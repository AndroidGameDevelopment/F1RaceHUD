package com.codaers.f1racehud.f1.game24

import com.codaers.f1racehud.PacketHeader
import java.nio.ByteBuffer
import android.util.Log

import java.nio.ByteOrder

// Packet IDs from F1 24 spec
const val PACKET_ID_MOTION: UByte = 0u
const val PACKET_ID_SESSION: UByte = 1u
const val PACKET_ID_LAP_DATA: UByte = 2u
const val PACKET_ID_EVENT: UByte = 3u
const val PACKET_ID_PARTICIPANTS: UByte = 4u
const val PACKET_ID_CAR_SETUPS: UByte = 5u
const val PACKET_ID_CAR_TELEMETRY: UByte = 6u
const val PACKET_ID_CAR_STATUS: UByte = 7u
const val PACKET_ID_FINAL_CLASSIFICATION: UByte = 8u
const val PACKET_ID_LOBBY_INFO: UByte = 9u
const val PACKET_ID_CAR_DAMAGE: UByte = 10u
const val PACKET_ID_SESSION_HISTORY: UByte = 11u
const val PACKET_ID_TYRE_SETS: UByte = 12u
const val PACKET_ID_MOTION_EX: UByte = 13u
const val PACKET_ID_TIME_TRIAL: UByte = 14u   // ✅ NEW in F1 24


object F1Parser24 {

    fun parse(buffer: ByteBuffer, header: PacketHeader): Any? {
        // Buffer is already positioned at the start of the payload here
        return when (header.packetId) {
            PACKET_ID_MOTION -> {
                val packet = parseMotion(buffer, header)
                Log.d("F1Parser", "Motion → Car0 pos=(${packet.carMotionData[0].worldPositionX}, ${packet.carMotionData[0].worldPositionY})")
                packet
            }
            PACKET_ID_SESSION -> {
                val packet = parseSession(buffer, header)
                Log.d("F1Parser", "Session → Weather=${packet.weather}, TrackTemp=${packet.trackTemperature}°C, AirTemp=${packet.airTemperature}°C")
                packet
            }
            PACKET_ID_LAP_DATA -> {
                val packet = parseLapData(buffer, header)
                val player = packet.lapData[header.playerCarIndex.toInt()]
                Log.d("F1Parser", "Lap → Current=${player.currentLapTimeInMS.toDouble() / 1000.0}s, Last=${player.lastLapTimeInMS.toDouble() / 1000.0}s")
                packet
            }
            PACKET_ID_EVENT -> {
                val packet = parseEvent(buffer, header)
                Log.d("F1Parser", "Event → Code=${packet.eventStringCode}")
                packet
            }
            PACKET_ID_PARTICIPANTS -> {
                val packet = parseParticipants(buffer, header)
                val player = packet.participants[header.playerCarIndex.toInt()]
                Log.d("F1Parser", "Driver → ${player.name}, Team=${player.teamId}, RaceNumber=${player.raceNumber}")
                packet
            }
            PACKET_ID_CAR_SETUPS -> {
                val packet = parseCarSetups(buffer, header)
                Log.d("F1Parser", "CarSetup → Player frontWing=${packet.carSetups[header.playerCarIndex.toInt()].frontWing}")
                packet
            }
            PACKET_ID_CAR_TELEMETRY -> {
                val packet = parseCarTelemetry(buffer, header)
                val player = packet.carTelemetryData[header.playerCarIndex.toInt()]
                Log.d("F1Parser", "Telemetry → Speed=${player.speed} km/h, RPM=${player.engineRPM}, Gear=${player.gear}")
                packet
            }
            PACKET_ID_CAR_STATUS -> {
                val packet = parseCarStatus(buffer, header)
                val player = packet.carStatusData[header.playerCarIndex.toInt()]
                Log.d("F1Parser", "CarStatus → Fuel=${player.fuelInTank}L, ERS=${player.ersStoreEnergy}")
                packet
            }
            PACKET_ID_FINAL_CLASSIFICATION -> {
                val packet = parseFinalClassification(buffer, header)
                val player = packet.classificationData[header.playerCarIndex.toInt()]
                Log.d("F1Parser", "Final → Pos=${player.position}, Points=${player.points}, Time=${player.totalRaceTime}")
                packet
            }
            PACKET_ID_LOBBY_INFO -> {
                val packet = parseLobbyInfo(buffer, header)
                Log.d("F1Parser", "Lobby → Players=${packet.numPlayers}")
                packet
            }
            PACKET_ID_CAR_DAMAGE -> {
                // Log raw bytes safely
                val pos = buffer.position()
                val raw = ByteArray(buffer.remaining())
                buffer.get(raw)
                buffer.position(pos)

                Log.d("RAW_PACKET_10", raw.joinToString(","))

                val packet = parseCarDamage(buffer, header)
                val player = packet.carDamageData[header.playerCarIndex.toInt()]
                Log.d("F1Parser", "Damage → FLWing=${player.frontLeftWingDamage}, Engine=${player.engineDamage}")
                packet
            }
            PACKET_ID_SESSION_HISTORY -> {
                val packet = parseSessionHistory(buffer, header)
                Log.d("F1Parser", "History → Laps=${packet.numLaps}, BestLap=${packet.bestLapTimeLapNum}")
                packet
            }
            PACKET_ID_TYRE_SETS -> {
                val packet = parseTyreSets(buffer, header)
                val player = packet.tyreSetData[packet.fittedIdx.toInt()]
                Log.d("F1Parser", "Tyre Set → Compound=${player.actualTyreCompound}, Wear=${player.wear}%")
                packet
            }
            PACKET_ID_MOTION_EX -> {
                val packet = parseMotionEx(buffer, header)
                Log.d("F1Parser", "MotionEx → SuspPosFL=${packet.suspensionPosition[2]}, WheelSpeedRR=${packet.wheelSpeed[1]}")
                packet
            }
            PACKET_ID_TIME_TRIAL -> {
                val packet = parseTimeTrialPacket(buffer, header)
                Log.d(
                    "F1Parser",
                    "TimeTrial → PlayerBest=${packet.playerSessionBestDataSet.lapTimeInMS}ms, " +
                            "PersonalBest=${packet.personalBestDataSet.lapTimeInMS}ms, " +
                            "Rival=${packet.rivalDataSet.lapTimeInMS}ms"
                )
                packet
            }
            else -> {
                Log.w("F1Parser", "Unhandled packet ${header.packetId}")
                null
            }
        }
    }

    private fun parseCarTelemetry(buffer: ByteBuffer, header: PacketHeader): PacketCarTelemetryData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        val cars = mutableListOf<CarTelemetryData>()

        repeat(22) {
            val speed = buffer.short.toUShort()
            val throttle = buffer.float
            val steer = buffer.float
            val brake = buffer.float
            val clutch = buffer.get().toUByte()
            val gear = buffer.get()
            val engineRPM = buffer.short.toUShort()
            val drs = buffer.get().toUByte()                        // 0 = off, 1 = on
            val revLightsPercent = buffer.get().toUByte()
            val revLightsBitValue = buffer.short.toUShort()
            val brakesTemperature = List(4) { buffer.short.toUShort() }
            val tyresSurfaceTemperature = List(4) { buffer.get().toUByte() }
            val tyresInnerTemperature = List(4) { buffer.get().toUByte() }
            val engineTemperature = buffer.short.toUShort()
            val tyresPressure = List(4) { buffer.float }
            val surfaceType = List(4) { buffer.get().toUByte() }

            cars.add(
                CarTelemetryData(
                    speed, throttle, steer, brake, clutch, gear, engineRPM, drs,
                    revLightsPercent, revLightsBitValue, brakesTemperature,
                    tyresSurfaceTemperature, tyresInnerTemperature, engineTemperature,
                    tyresPressure, surfaceType
                )
            )
        }

        val mfdPanelIndex = buffer.get().toUByte()
        val mfdPanelIndexSecondaryPlayer = buffer.get().toUByte()
        val suggestedGear = buffer.get()

        return PacketCarTelemetryData(
            header = header,
            carTelemetryData = cars,
            mfdPanelIndex = mfdPanelIndex,
            mfdPanelIndexSecondaryPlayer = mfdPanelIndexSecondaryPlayer,
            suggestedGear = suggestedGear
        )
    }

    private fun parseLapData(buffer: ByteBuffer, header: PacketHeader): PacketLapData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        val laps = mutableListOf<LapData>()

        repeat(22) {
            val lastLapTimeInMS = buffer.int.toUInt()
            val currentLapTimeInMS = buffer.int.toUInt()

            val sector1TimeMSPart = buffer.short.toUShort()
            val sector1TimeMinutesPart = buffer.get().toUByte()

            val sector2TimeMSPart = buffer.short.toUShort()
            val sector2TimeMinutesPart = buffer.get().toUByte()

            val deltaToCarInFrontMSPart = buffer.short.toUShort()
            val deltaToCarInFrontMinutesPart = buffer.get().toUByte()

            val deltaToRaceLeaderMSPart = buffer.short.toUShort()
            val deltaToRaceLeaderMinutesPart = buffer.get().toUByte()

            val lapDistance = buffer.float
            val totalDistance = buffer.float
            val safetyCarDelta = buffer.float

            val carPosition = buffer.get().toUByte()
            val currentLapNum = buffer.get().toUByte()
            val pitStatus = buffer.get().toUByte()
            val numPitStops = buffer.get().toUByte()
            val sector = buffer.get().toUByte()
            val currentLapInvalid = buffer.get().toUByte()
            val penalties = buffer.get().toUByte()
            val totalWarnings = buffer.get().toUByte()
            val cornerCuttingWarnings = buffer.get().toUByte()
            val numUnservedDriveThroughPens = buffer.get().toUByte()
            val numUnservedStopGoPens = buffer.get().toUByte()
            val gridPosition = buffer.get().toUByte()
            val driverStatus = buffer.get().toUByte()
            val resultStatus = buffer.get().toUByte()
            val pitLaneTimerActive = buffer.get().toUByte()
            val pitLaneTimeInLaneInMS = buffer.short.toUShort()
            val pitStopTimerInMS = buffer.short.toUShort()
            val pitStopShouldServePen = buffer.get().toUByte()

            val speedTrapFastestSpeed = buffer.float
            val speedTrapFastestLap = buffer.get().toUByte()

            laps.add(
                LapData(
                    lastLapTimeInMS,
                    currentLapTimeInMS,
                    sector1TimeMSPart,
                    sector1TimeMinutesPart,
                    sector2TimeMSPart,
                    sector2TimeMinutesPart,
                    deltaToCarInFrontMSPart,
                    deltaToCarInFrontMinutesPart,
                    deltaToRaceLeaderMSPart,
                    deltaToRaceLeaderMinutesPart,
                    lapDistance,
                    totalDistance,
                    safetyCarDelta,
                    carPosition,
                    currentLapNum,
                    pitStatus,
                    numPitStops,
                    sector,
                    currentLapInvalid,
                    penalties,
                    totalWarnings,
                    cornerCuttingWarnings,
                    numUnservedDriveThroughPens,
                    numUnservedStopGoPens,
                    gridPosition,
                    driverStatus,
                    resultStatus,
                    pitLaneTimerActive,
                    pitLaneTimeInLaneInMS,
                    pitStopTimerInMS,
                    pitStopShouldServePen,
                    speedTrapFastestSpeed,
                    speedTrapFastestLap
                )
            )
        }

        val timeTrialPBCarIdx = buffer.get().toUByte()
        val timeTrialRivalCarIdx = buffer.get().toUByte()

        return PacketLapData(header, laps, timeTrialPBCarIdx, timeTrialRivalCarIdx)
    }

    private fun parseSession(buffer: ByteBuffer, header: PacketHeader): PacketSessionData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        val weather = buffer.get().toUByte()
        val trackTemperature = buffer.get()
        val airTemperature = buffer.get()
        val totalLaps = buffer.get().toUByte()
        val trackLength = buffer.short.toUShort()
        val sessionType = buffer.get().toUByte()
        val trackId = buffer.get()
        val formula = buffer.get().toUByte()
        val sessionTimeLeft = buffer.short.toUShort()
        val sessionDuration = buffer.short.toUShort()
        val pitSpeedLimit = buffer.get().toUByte()
        val gamePaused = buffer.get().toUByte()
        val isSpectating = buffer.get().toUByte()
        val spectatorCarIndex = buffer.get().toUByte()
        val sliProNativeSupport = buffer.get().toUByte()
        val numMarshalZones = buffer.get().toUByte()

        val marshalZones = mutableListOf<MarshalZone>()
        repeat(numMarshalZones.toInt()) {
            val zoneStart = buffer.float
            val zoneFlag = buffer.get()
            marshalZones.add(MarshalZone(zoneStart, zoneFlag))
        }

        val safetyCarStatus = buffer.get().toUByte()
        val networkGame = buffer.get().toUByte()

        val numWeatherForecastSamples = buffer.get().toUByte()
        val weatherForecastSamples = mutableListOf<WeatherForecastSample>()
        repeat(numWeatherForecastSamples.toInt()) {
            val sessionTypeSample = buffer.get().toUByte()
            val timeOffset = buffer.get().toUByte()
            val weatherSample = buffer.get().toUByte()
            val trackTemp = buffer.get()
            val trackTempChange = buffer.get()
            val airTemp = buffer.get()
            val airTempChange = buffer.get()
            val rainPercentage = buffer.get().toUByte()
            weatherForecastSamples.add(
                WeatherForecastSample(
                    sessionTypeSample,
                    timeOffset,
                    weatherSample,
                    trackTemp,
                    trackTempChange,
                    airTemp,
                    airTempChange,
                    rainPercentage
                )
            )
        }

        val forecastAccuracy = buffer.get().toUByte()
        val aiDifficulty = buffer.get().toUByte()
        val seasonLinkIdentifier = buffer.int.toUInt()
        val weekendLinkIdentifier = buffer.int.toUInt()
        val sessionLinkIdentifier = buffer.int.toUInt()

        val pitStopWindowIdealLap = buffer.get().toUByte()
        val pitStopWindowLatestLap = buffer.get().toUByte()
        val pitStopRejoinPosition = buffer.get().toUByte()

        val steeringAssist = buffer.get().toUByte()
        val brakingAssist = buffer.get().toUByte()
        val gearboxAssist = buffer.get().toUByte()
        val pitAssist = buffer.get().toUByte()
        val pitReleaseAssist = buffer.get().toUByte()
        @Suppress("LocalVariableName") val ERSAssist = buffer.get().toUByte()
        @Suppress("LocalVariableName") val DRSAssist = buffer.get().toUByte()
        val dynamicRacingLine = buffer.get().toUByte()
        val dynamicRacingLineType = buffer.get().toUByte()

        val gameMode = buffer.get().toUByte()
        val ruleSet = buffer.get().toUByte()
        val timeOfDay = buffer.int.toUInt()
        val sessionLength = buffer.get().toUByte()

        val speedUnitsLeadPlayer = buffer.get().toUByte()
        val temperatureUnitsLeadPlayer = buffer.get().toUByte()
        val speedUnitsSecondaryPlayer = buffer.get().toUByte()
        val temperatureUnitsSecondaryPlayer = buffer.get().toUByte()

        val numSafetyCarPeriods = buffer.get().toUByte()
        val numVirtualSafetyCarPeriods = buffer.get().toUByte()
        val numRedFlagPeriods = buffer.get().toUByte()

        val equalCarPerformance = buffer.get().toUByte()
        val recoveryMode = buffer.get().toUByte()
        val flashbackLimit = buffer.get().toUByte()
        val surfaceType = buffer.get().toUByte()
        val lowFuelMode = buffer.get().toUByte()
        val raceStarts = buffer.get().toUByte()
        val tyreTemperature = buffer.get().toUByte()
        val pitLaneTyreSim = buffer.get().toUByte()
        val carDamage = buffer.get().toUByte()
        val carDamageRate = buffer.get().toUByte()
        val collisions = buffer.get().toUByte()
        val collisionsOffForFirstLapOnly = buffer.get().toUByte()
        val mpUnsafePitRelease = buffer.get().toUByte()
        val mpOffForGriefing = buffer.get().toUByte()
        val cornerCuttingStringency = buffer.get().toUByte()
        val parcFermeRules = buffer.get().toUByte()
        val pitStopExperience = buffer.get().toUByte()
        val safetyCar = buffer.get().toUByte()
        val safetyCarExperience = buffer.get().toUByte()
        val formationLap = buffer.get().toUByte()
        val formationLapExperience = buffer.get().toUByte()
        val redFlags = buffer.get().toUByte()
        val affectsLicenceLevelSolo = buffer.get().toUByte()
        val affectsLicenceLevelMP = buffer.get().toUByte()
        val numSessionsInWeekend = buffer.get().toUByte()

        val weekendStructure = MutableList(12) { buffer.get().toUByte() }

        val sector2LapDistanceStart = buffer.float
        val sector3LapDistanceStart = buffer.float

        return PacketSessionData(
            header = header,
            weather = weather,
            trackTemperature = trackTemperature,
            airTemperature = airTemperature,
            totalLaps = totalLaps,
            trackLength = trackLength,
            sessionType = sessionType,
            trackId = trackId,
            formula = formula,
            sessionTimeLeft = sessionTimeLeft,
            sessionDuration = sessionDuration,
            pitSpeedLimit = pitSpeedLimit,
            gamePaused = gamePaused,
            isSpectating = isSpectating,
            spectatorCarIndex = spectatorCarIndex,
            sliProNativeSupport = sliProNativeSupport,
            numMarshalZones = numMarshalZones,
            marshalZones = marshalZones,
            safetyCarStatus = safetyCarStatus,
            networkGame = networkGame,
            numWeatherForecastSamples = numWeatherForecastSamples,
            weatherForecastSamples = weatherForecastSamples,
            forecastAccuracy = forecastAccuracy,
            aiDifficulty = aiDifficulty,
            seasonLinkIdentifier = seasonLinkIdentifier,
            weekendLinkIdentifier = weekendLinkIdentifier,
            sessionLinkIdentifier = sessionLinkIdentifier,
            pitStopWindowIdealLap = pitStopWindowIdealLap,
            pitStopWindowLatestLap = pitStopWindowLatestLap,
            pitStopRejoinPosition = pitStopRejoinPosition,
            steeringAssist = steeringAssist,
            brakingAssist = brakingAssist,
            gearboxAssist = gearboxAssist,
            pitAssist = pitAssist,
            pitReleaseAssist = pitReleaseAssist,
            ERSAssist = ERSAssist,
            DRSAssist = DRSAssist,
            dynamicRacingLine = dynamicRacingLine,
            dynamicRacingLineType = dynamicRacingLineType,
            gameMode = gameMode,
            ruleSet = ruleSet,
            timeOfDay = timeOfDay,
            sessionLength = sessionLength,
            speedUnitsLeadPlayer = speedUnitsLeadPlayer,
            temperatureUnitsLeadPlayer = temperatureUnitsLeadPlayer,
            speedUnitsSecondaryPlayer = speedUnitsSecondaryPlayer,
            temperatureUnitsSecondaryPlayer = temperatureUnitsSecondaryPlayer,
            numSafetyCarPeriods = numSafetyCarPeriods,
            numVirtualSafetyCarPeriods = numVirtualSafetyCarPeriods,
            numRedFlagPeriods = numRedFlagPeriods,
            equalCarPerformance = equalCarPerformance,
            recoveryMode = recoveryMode,
            flashbackLimit = flashbackLimit,
            surfaceType = surfaceType,
            lowFuelMode = lowFuelMode,
            raceStarts = raceStarts,
            tyreTemperature = tyreTemperature,
            pitLaneTyreSim = pitLaneTyreSim,
            carDamage = carDamage,
            carDamageRate = carDamageRate,
            collisions = collisions,
            collisionsOffForFirstLapOnly = collisionsOffForFirstLapOnly,
            mpUnsafePitRelease = mpUnsafePitRelease,
            mpOffForGriefing = mpOffForGriefing,
            cornerCuttingStringency = cornerCuttingStringency,
            parcFermeRules = parcFermeRules,
            pitStopExperience = pitStopExperience,
            safetyCar = safetyCar,
            safetyCarExperience = safetyCarExperience,
            formationLap = formationLap,
            formationLapExperience = formationLapExperience,
            redFlags = redFlags,
            affectsLicenceLevelSolo = affectsLicenceLevelSolo,
            affectsLicenceLevelMP = affectsLicenceLevelMP,
            numSessionsInWeekend = numSessionsInWeekend,
            weekendStructure = weekendStructure,
            sector2LapDistanceStart = sector2LapDistanceStart,
            sector3LapDistanceStart = sector3LapDistanceStart
        )
    }

    private fun parseMotion(buffer: ByteBuffer, header: PacketHeader): PacketMotionData {
        val cars = mutableListOf<CarMotionData>()

        repeat(22) {
            val worldPositionX = buffer.float
            val worldPositionY = buffer.float
            val worldPositionZ = buffer.float
            val worldVelocityX = buffer.float
            val worldVelocityY = buffer.float
            val worldVelocityZ = buffer.float

            val worldForwardDirX = buffer.short
            val worldForwardDirY = buffer.short
            val worldForwardDirZ = buffer.short
            val worldRightDirX = buffer.short
            val worldRightDirY = buffer.short
            val worldRightDirZ = buffer.short

            val gForceLateral = buffer.float
            val gForceLongitudinal = buffer.float
            val gForceVertical = buffer.float

            val yaw = buffer.float
            val pitch = buffer.float
            val roll = buffer.float

            cars.add(
                CarMotionData(
                    worldPositionX, worldPositionY, worldPositionZ,
                    worldVelocityX, worldVelocityY, worldVelocityZ,
                    worldForwardDirX, worldForwardDirY, worldForwardDirZ,
                    worldRightDirX, worldRightDirY, worldRightDirZ,
                    gForceLateral, gForceLongitudinal, gForceVertical,
                    yaw, pitch, roll
                )
            )
        }

        return PacketMotionData(header, cars)
    }

    private fun parseEvent(buffer: ByteBuffer, header: PacketHeader): PacketEventData {
        val codeBytes = ByteArray(4)
        buffer.get(codeBytes)
        val code = codeBytes.toString(Charsets.UTF_8).trimEnd('\u0000')

        val details: EventDataDetails = when (code) {
            "FTLP" -> {
                val vehicleIdx = buffer.get().toUByte()
                val lapTime = buffer.float
                EventDataDetails.FastestLap(vehicleIdx, lapTime)
            }

            "RTMT" -> {
                val vehicleIdx = buffer.get().toUByte()
                EventDataDetails.Retirement(vehicleIdx)
            }

            "TMPT" -> {
                val vehicleIdx = buffer.get().toUByte()
                EventDataDetails.TeamMateInPits(vehicleIdx)
            }

            "RCWN" -> {
                val vehicleIdx = buffer.get().toUByte()
                EventDataDetails.RaceWinner(vehicleIdx)
            }

            "PENA" -> {
                val penaltyType = buffer.get().toUByte()
                val infringementType = buffer.get().toUByte()
                val vehicleIdx = buffer.get().toUByte()
                val otherVehicleIdx = buffer.get().toUByte()
                val time = buffer.get().toUByte()
                val lapNum = buffer.get().toUByte()
                val placesGained = buffer.get().toUByte()
                EventDataDetails.Penalty(
                    penaltyType,
                    infringementType,
                    vehicleIdx,
                    otherVehicleIdx,
                    time,
                    lapNum,
                    placesGained
                )
            }

            "SPTP" -> {
                val vehicleIdx = buffer.get().toUByte()
                val speed = buffer.float
                val isOverallFastest = buffer.get().toUByte()
                val isDriverFastest = buffer.get().toUByte()
                val fastestVehicleIdx = buffer.get().toUByte()
                val fastestSpeed = buffer.float
                EventDataDetails.SpeedTrap(
                    vehicleIdx,
                    speed,
                    isOverallFastest,
                    isDriverFastest,
                    fastestVehicleIdx,
                    fastestSpeed
                )
            }

            "STLG" -> {
                val numLights = buffer.get().toUByte()
                EventDataDetails.StartLights(numLights)
            }

            "LGOT" -> {
                // Lights out has NO payload
                EventDataDetails.StartLights(0u)
            }

            "DTSV" -> {
                val vehicleIdx = buffer.get().toUByte()
                EventDataDetails.DriveThroughPenaltyServed(vehicleIdx)
            }

            "SGSV" -> {
                val vehicleIdx = buffer.get().toUByte()
                EventDataDetails.StopGoPenaltyServed(vehicleIdx)
            }

            "FLBK" -> {
                val frameId = buffer.int.toUInt()
                val sessionTime = buffer.float
                EventDataDetails.Flashback(frameId, sessionTime)
            }

            "BUTN" -> {
                val buttonStatus = buffer.int.toUInt()
                EventDataDetails.Buttons(buttonStatus)
            }

            "RDFL" -> {
                // Red flag has NO payload
                EventDataDetails.StartLights(0u)
            }

            "OVTK" -> {
                val overtaking = buffer.get().toUByte()
                val overtaken = buffer.get().toUByte()
                EventDataDetails.Overtake(overtaking, overtaken)
            }

            "SCAR" -> {
                val safetyCarType = buffer.get().toUByte()
                val eventType = buffer.get().toUByte()
                EventDataDetails.SafetyCar(safetyCarType, eventType)
            }

            "COLL" -> {
                val v1 = buffer.get().toUByte()
                val v2 = buffer.get().toUByte()
                EventDataDetails.Collision(v1, v2)
            }

            else -> {
                // Unknown event → no payload
                EventDataDetails.Buttons(0u)
            }
        }

        return PacketEventData(header, code, details)
    }

    private fun parseParticipants(buffer: ByteBuffer, header: PacketHeader): PacketParticipantsData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        val numActiveCars = buffer.get().toUByte()
        val participants = mutableListOf<ParticipantData>()

        repeat(numActiveCars.toInt()) {
            val aiControlled = buffer.get().toUByte()
            val driverId = buffer.get().toUByte()
            val networkId = buffer.get().toUByte()
            val teamId = buffer.get().toUByte()
            val myTeam = buffer.get().toUByte()
            val raceNumber = buffer.get().toUByte()
            val nationality = buffer.get().toUByte()

            val nameBytes = ByteArray(48)
            buffer.get(nameBytes)
            val name = nameBytes.toString(Charsets.UTF_8).trimEnd('\u0000')

            val yourTelemetry = buffer.get().toUByte()
            val showOnlineNames = buffer.get().toUByte()

            val techLevel = buffer.short.toUShort()   // ✅ NEW in F1 24 (uint16)

            val platform = buffer.get().toUByte()

            participants.add(
                ParticipantData(
                    aiControlled = aiControlled,
                    driverId = driverId,
                    networkId = networkId,
                    teamId = teamId,
                    myTeam = myTeam,
                    raceNumber = raceNumber,
                    nationality = nationality,
                    name = name,
                    yourTelemetry = yourTelemetry,
                    showOnlineNames = showOnlineNames,
                    techLevel = techLevel,      // ✅ inserted in correct position
                    platform = platform
                )
            )
        }

        return PacketParticipantsData(header, numActiveCars, participants)
    }

    private fun parseCarSetups(buffer: ByteBuffer, header: PacketHeader): PacketCarSetupData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        val setups = mutableListOf<CarSetupData>()

        repeat(22) {
            val frontWing = buffer.get().toUByte()
            val rearWing = buffer.get().toUByte()
            val onThrottle = buffer.get().toUByte()
            val offThrottle = buffer.get().toUByte()
            val frontCamber = buffer.float
            val rearCamber = buffer.float
            val frontToe = buffer.float
            val rearToe = buffer.float
            val frontSuspension = buffer.get().toUByte()
            val rearSuspension = buffer.get().toUByte()
            val frontAntiRollBar = buffer.get().toUByte()
            val rearAntiRollBar = buffer.get().toUByte()
            val frontSuspensionHeight = buffer.get().toUByte()
            val rearSuspensionHeight = buffer.get().toUByte()
            val brakePressure = buffer.get().toUByte()
            val brakeBias = buffer.get().toUByte()
            val engineBraking = buffer.get().toUByte()          // ✅ NEW in F1 24
            val rearLeftTyrePressure = buffer.float
            val rearRightTyrePressure = buffer.float
            val frontLeftTyrePressure = buffer.float
            val frontRightTyrePressure = buffer.float
            val ballast = buffer.get().toUByte()
            val fuelLoad = buffer.float

            setups.add(
                CarSetupData(
                    frontWing, rearWing, onThrottle, offThrottle,
                    frontCamber, rearCamber, frontToe, rearToe,
                    frontSuspension, rearSuspension,
                    frontAntiRollBar, rearAntiRollBar,
                    frontSuspensionHeight, rearSuspensionHeight,
                    brakePressure, brakeBias, engineBraking,          // ✅ inserted here
                    rearLeftTyrePressure, rearRightTyrePressure,
                    frontLeftTyrePressure, frontRightTyrePressure,
                    ballast, fuelLoad
                )
            )
        }

        val nextFrontWingValue = buffer.float                     // ✅ NEW in F1 24

        return PacketCarSetupData(header, setups, nextFrontWingValue)
    }

    private fun parseCarStatus(buffer: ByteBuffer, header: PacketHeader): PacketCarStatusData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        val statuses = mutableListOf<CarStatusData>()

        repeat(22) {
            val tractionControl = buffer.get().toUByte()
            val antiLockBrakes = buffer.get().toUByte()
            val fuelMix = buffer.get().toUByte()
            val frontBrakeBias = buffer.get().toUByte()
            val pitLimiterStatus = buffer.get().toUByte()
            val fuelInTank = buffer.float
            val fuelCapacity = buffer.float
            val fuelRemainingLaps = buffer.float
            val maxRPM = buffer.short.toUShort()
            val idleRPM = buffer.short.toUShort()
            val maxGears = buffer.get().toUByte()
            val drsAllowed = buffer.get().toUByte()     // 0 = not allowed, 1 = allowed
            val drsActivationDistance = buffer.short.toUShort() // 0 = DRS not available, non-zero - DRS will be available in [X] metres
            val actualTyreCompound = buffer.get().toUByte()
            val visualTyreCompound = buffer.get().toUByte()
            val tyresAgeLaps = buffer.get().toUByte()
            val vehicleFiaFlags = buffer.get() // Byte
            val enginePowerICE = buffer.float
            val enginePowerMGUK = buffer.float
            val ersStoreEnergy = buffer.float
            val ersDeployMode = buffer.get().toUByte()
            val ersHarvestedThisLapMGUK = buffer.float
            val ersHarvestedThisLapMGUH = buffer.float
            val ersDeployedThisLap = buffer.float
            val networkPaused = buffer.get().toUByte()

            statuses.add(
                CarStatusData(
                    tractionControl, antiLockBrakes, fuelMix, frontBrakeBias, pitLimiterStatus,
                    fuelInTank, fuelCapacity, fuelRemainingLaps,
                    maxRPM, idleRPM, maxGears, drsAllowed, drsActivationDistance,
                    actualTyreCompound, visualTyreCompound, tyresAgeLaps, vehicleFiaFlags,
                    enginePowerICE, enginePowerMGUK, ersStoreEnergy, ersDeployMode,
                    ersHarvestedThisLapMGUK, ersHarvestedThisLapMGUH, ersDeployedThisLap,
                    networkPaused
                )
            )
        }

        return PacketCarStatusData(header, statuses)
    }

    private fun parseFinalClassification(buffer: ByteBuffer, header: PacketHeader): PacketFinalClassificationData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        val numCars = buffer.get().toUByte()
        val classificationData = mutableListOf<FinalClassificationData>()

        repeat(numCars.toInt()) {
            val position = buffer.get().toUByte()
            val numLaps = buffer.get().toUByte()
            val gridPosition = buffer.get().toUByte()
            val points = buffer.get().toUByte()
            val numPitStops = buffer.get().toUByte()
            val resultStatus = buffer.get().toUByte()
            val bestLapTimeMs = buffer.int.toUInt()
            val totalRaceTime = buffer.double
            val penaltiesTime = buffer.get().toUByte()
            val numPenalties = buffer.get().toUByte()
            val numTyreStints = buffer.get().toUByte()

            // Always read 8 entries for each array
            val tyreStintsActual = List(8) { buffer.get().toUByte() }
            val tyreStintsVisual = List(8) { buffer.get().toUByte() }
            val tyreStintsEndLap = List(8) { buffer.get().toUByte() }

            classificationData.add(
                FinalClassificationData(
                    position, numLaps, gridPosition, points, numPitStops,
                    resultStatus, bestLapTimeMs, totalRaceTime,
                    penaltiesTime, numPenalties, numTyreStints,
                    tyreStintsActual, tyreStintsVisual, tyreStintsEndLap
                )
            )
        }

        return PacketFinalClassificationData(header, numCars, classificationData)
    }

    private fun parseLobbyInfo(buffer: ByteBuffer, header: PacketHeader): PacketLobbyInfoData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        val numPlayers = buffer.get().toUByte()
        val lobbyPlayers = mutableListOf<LobbyInfoData>()

        repeat(numPlayers.toInt()) {
            val aiControlled = buffer.get().toUByte()
            val teamId = buffer.get().toUByte()
            val nationality = buffer.get().toUByte()
            val platform = buffer.get().toUByte()

            val nameBytes = ByteArray(48)
            buffer.get(nameBytes)
            val name = nameBytes.toString(Charsets.UTF_8).trimEnd('\u0000')

            val carNumber = buffer.get().toUByte()
            val yourTelemetry = buffer.get().toUByte()
            val showOnlineNames = buffer.get().toUByte()
            val techLevel = buffer.short.toUShort()
            val readyStatus = buffer.get().toUByte()

            lobbyPlayers.add(
                LobbyInfoData(
                    aiControlled = aiControlled,
                    teamId = teamId,
                    nationality = nationality,
                    platform = platform,
                    name = name,
                    carNumber = carNumber,
                    yourTelemetry = yourTelemetry,
                    showOnlineNames = showOnlineNames,
                    techLevel = techLevel,
                    readyStatus = readyStatus
                )
            )
        }

        return PacketLobbyInfoData(header, numPlayers, lobbyPlayers)
    }

    private fun parseCarDamage(buffer: ByteBuffer, header: PacketHeader): PacketCarDamageData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        val damages = mutableListOf<CarDamageData>()

        repeat(22) {
            val tyresWear = List(4) { buffer.float }
            val tyresDamage = List(4) { buffer.get().toUByte() }
            val brakesDamage = List(4) { buffer.get().toUByte() }
            val frontLeftWingDamage = buffer.get().toUByte()
            val frontRightWingDamage = buffer.get().toUByte()
            val rearWingDamage = buffer.get().toUByte()
            val floorDamage = buffer.get().toUByte()
            val diffuserDamage = buffer.get().toUByte()
            val sidepodDamage = buffer.get().toUByte()
            val drsFault = buffer.get().toUByte()
            val ersFault = buffer.get().toUByte()          // <-- new
            val gearBoxDamage = buffer.get().toUByte()
            val engineDamage = buffer.get().toUByte()
            val engineMGUHWear = buffer.get().toUByte()
            val engineESWear = buffer.get().toUByte()
            val engineCEWear = buffer.get().toUByte()
            val engineICEWear = buffer.get().toUByte()
            val engineMGUKWear = buffer.get().toUByte()
            val engineTCWear = buffer.get().toUByte()
            val engineBlown = buffer.get().toUByte()       // <-- new
            val engineSeized = buffer.get().toUByte()      // <-- new

            damages.add(
                CarDamageData(
                    tyresWear, tyresDamage, brakesDamage,
                    frontLeftWingDamage, frontRightWingDamage, rearWingDamage,
                    floorDamage, diffuserDamage, sidepodDamage,
                    drsFault, ersFault, gearBoxDamage, engineDamage,
                    engineMGUHWear, engineESWear, engineCEWear,
                    engineICEWear, engineMGUKWear, engineTCWear,
                    engineBlown, engineSeized
                )
            )
        }

        return PacketCarDamageData(header, damages)
    }

    private fun parseSessionHistory(buffer: ByteBuffer, header: PacketHeader): PacketSessionHistoryData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        val carIndex = buffer.get().toUByte()
        val numLaps = buffer.get().toUByte()
        val numTyreStints = buffer.get().toUByte()
        val bestLapTimeLapNum = buffer.get().toUByte()
        val bestSector1LapNum = buffer.get().toUByte()
        val bestSector2LapNum = buffer.get().toUByte()
        val bestSector3LapNum = buffer.get().toUByte()

        val lapHistoryData = mutableListOf<LapHistoryData>()
        repeat(minOf(numLaps.toInt(), 100)) {
            val lapTimeMs = buffer.int.toUInt()
            val sector1TimeMs = buffer.short.toUShort()
            val sector1TimeMinutes = buffer.get().toUByte()
            val sector2TimeMs = buffer.short.toUShort()
            val sector2TimeMinutes = buffer.get().toUByte()
            val sector3TimeMs = buffer.short.toUShort()
            val sector3TimeMinutes = buffer.get().toUByte()
            val lapValidBitFlags = buffer.get().toUByte()

            lapHistoryData.add(
                LapHistoryData(
                    lapTimeMs,
                    sector1TimeMs,
                    sector1TimeMinutes,
                    sector2TimeMs,
                    sector2TimeMinutes,
                    sector3TimeMs,
                    sector3TimeMinutes,
                    lapValidBitFlags
                )
            )
        }

        val tyreStintsHistoryData = mutableListOf<TyreStintHistoryData>()
        repeat(minOf(numTyreStints.toInt(), 8)) {
            val endLap = buffer.get().toUByte()
            val actualCompound = buffer.get().toUByte()
            val visualCompound = buffer.get().toUByte()

            tyreStintsHistoryData.add(
                TyreStintHistoryData(endLap, actualCompound, visualCompound)
            )
        }

        return PacketSessionHistoryData(
            header,
            carIndex,
            numLaps,
            numTyreStints,
            bestLapTimeLapNum,
            bestSector1LapNum,
            bestSector2LapNum,
            bestSector3LapNum,
            lapHistoryData,
            tyreStintsHistoryData
        )
    }

    private fun parseTyreSets(buffer: ByteBuffer, header: PacketHeader): PacketTyreSetsData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        val carIdx = buffer.get().toUByte()
        val tyreSets = mutableListOf<TyreSetData>()

        repeat(20) {
            val actualTyreCompound = buffer.get().toUByte()
            val visualTyreCompound = buffer.get().toUByte()
            val wear = buffer.get().toUByte()
            val available = buffer.get().toUByte()
            val recommendedSession = buffer.get().toUByte()
            val lifeSpan = buffer.get().toUByte()
            val usableLife = buffer.get().toUByte()
            val lapDeltaTime = buffer.short // signed int16
            val fitted = buffer.get().toUByte()

            tyreSets.add(
                TyreSetData(
                    actualTyreCompound,
                    visualTyreCompound,
                    wear,
                    available,
                    recommendedSession,
                    lifeSpan,
                    usableLife,
                    lapDeltaTime,
                    fitted
                )
            )
        }

        val fittedIdx = buffer.get().toUByte()

        return PacketTyreSetsData(header, carIdx, tyreSets, fittedIdx)
    }

    private fun parseMotionEx(buffer: ByteBuffer, header: PacketHeader): PacketMotionExData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        fun readFloatArray(count: Int) = List(count) { buffer.float }

        val suspensionPosition = readFloatArray(4)
        val suspensionVelocity = readFloatArray(4)
        val suspensionAcceleration = readFloatArray(4)
        val wheelSpeed = readFloatArray(4)
        val wheelSlipRatio = readFloatArray(4)
        val wheelSlipAngle = readFloatArray(4)
        val wheelLatForce = readFloatArray(4)
        val wheelLongForce = readFloatArray(4)

        val heightOfCOGAboveGround = buffer.float
        val localVelocityX = buffer.float
        val localVelocityY = buffer.float
        val localVelocityZ = buffer.float

        val angularVelocityX = buffer.float
        val angularVelocityY = buffer.float
        val angularVelocityZ = buffer.float

        val angularAccelerationX = buffer.float
        val angularAccelerationY = buffer.float
        val angularAccelerationZ = buffer.float

        val frontWheelsAngle = buffer.float

        val wheelVertForce = readFloatArray(4)

        val frontAeroHeight = buffer.float       // ✅ NEW in 24
        val rearAeroHeight = buffer.float        // ✅ NEW in 24
        val frontRollAngle = buffer.float        // ✅ NEW in 24
        val rearRollAngle = buffer.float         // ✅ NEW in 24
        val chassisYaw = buffer.float            // ✅ NEW in 24

        return PacketMotionExData(
            header,
            suspensionPosition,
            suspensionVelocity,
            suspensionAcceleration,
            wheelSpeed,
            wheelSlipRatio,
            wheelSlipAngle,
            wheelLatForce,
            wheelLongForce,
            heightOfCOGAboveGround,
            localVelocityX,
            localVelocityY,
            localVelocityZ,
            angularVelocityX,
            angularVelocityY,
            angularVelocityZ,
            angularAccelerationX,
            angularAccelerationY,
            angularAccelerationZ,
            frontWheelsAngle,
            wheelVertForce,
            frontAeroHeight,
            rearAeroHeight,
            frontRollAngle,
            rearRollAngle,
            chassisYaw
        )
    }

    private fun parseTimeTrialDataSet(buffer: ByteBuffer): TimeTrialDataSet {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        val carIdx = buffer.get().toUByte()
        val teamId = buffer.get().toUByte()
        val lapTimeInMS = buffer.int.toUInt()
        val sector1TimeInMS = buffer.int.toUInt()
        val sector2TimeInMS = buffer.int.toUInt()
        val sector3TimeInMS = buffer.int.toUInt()
        val tractionControl = buffer.get().toUByte()
        val gearboxAssist = buffer.get().toUByte()
        val antiLockBrakes = buffer.get().toUByte()
        val equalCarPerformance = buffer.get().toUByte()
        val customSetup = buffer.get().toUByte()
        val valid = buffer.get().toUByte()

        return TimeTrialDataSet(
            carIdx,
            teamId,
            lapTimeInMS,
            sector1TimeInMS,
            sector2TimeInMS,
            sector3TimeInMS,
            tractionControl,
            gearboxAssist,
            antiLockBrakes,
            equalCarPerformance,
            customSetup,
            valid
        )
    }

    private fun parseTimeTrialPacket(buffer: ByteBuffer, header: PacketHeader): PacketTimeTrialData {
        buffer.order(ByteOrder.LITTLE_ENDIAN)

        val playerSessionBest = parseTimeTrialDataSet(buffer)
        val personalBest = parseTimeTrialDataSet(buffer)
        val rival = parseTimeTrialDataSet(buffer)

        return PacketTimeTrialData(
            header,
            playerSessionBest,
            personalBest,
            rival
        )
    }
}
