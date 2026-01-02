package com.codaers.f1racehud.f1.game24

import com.codaers.f1racehud.f1.F1Metadata

object F1Formatters {
    fun formatMotion(p: PacketMotionData) = formatMotion24(p)
    fun formatSession(p: PacketSessionData) = formatSession24(p)
    fun formatLapData(p: PacketLapData) = formatLapData24(p)
    fun formatEvent(p: PacketEventData) = formatEvent24(p)
    fun formatParticipants(p: PacketParticipantsData) = formatParticipants24(p)
    fun formatCarSetups(p: PacketCarSetupData) = formatCarSetups24(p)
    fun formatCarTelemetry(p: PacketCarTelemetryData) = formatCarTelemetry24(p)
    fun formatCarStatus(p: PacketCarStatusData) = formatCarStatus24(p)
    fun formatFinalClassification(p: PacketFinalClassificationData) = formatFinalClassification24(p)
    fun formatLobbyInfo(p: PacketLobbyInfoData) = formatLobbyInfo24(p)
    fun formatCarDamage(p: PacketCarDamageData) = formatCarDamage24(p)
    fun formatSessionHistory(p: PacketSessionHistoryData) = formatSessionHistory24(p)
    fun formatTyreSets(p: PacketTyreSetsData) = formatTyreSets24(p)
    fun formatMotionEx(p: PacketMotionExData) = formatMotionEx24(p)

    fun formatTimeTrial(p: PacketTimeTrialData) = formatTimeTrial24(p)
}

// Motion Data (ID 0)
fun formatMotion24(packet: PacketMotionData): String {

    // Motion Data (ID 0)
    val car0 = packet.carMotionData[packet.header.playerCarIndex.toInt()]
    return """
        Position: (X=${car0.worldPositionX}, Y=${car0.worldPositionY}, Z=${car0.worldPositionZ})
        Velocity: (X=${car0.worldVelocityX}, Y=${car0.worldVelocityY}, Z=${car0.worldVelocityZ})
        Forward Dir: (X=${car0.worldForwardDirX}, Y=${car0.worldForwardDirY}, Z=${car0.worldForwardDirZ})
        Right Dir: (X=${car0.worldRightDirX}, Y=${car0.worldRightDirY}, Z=${car0.worldRightDirZ})
        G-Force: (Lat=${car0.gForceLateral}, Long=${car0.gForceLongitudinal}, Vert=${car0.gForceVertical})
        Orientation: (Yaw=${car0.yaw}, Pitch=${car0.pitch}, Roll=${car0.roll})
    """.trimIndent()
}

// Session Data (ID 1)
fun formatSession24(packet: PacketSessionData): String {
    val forecasts = packet.weatherForecastSamples.take(3).joinToString("\n") { f ->
        "  +${f.timeOffset}min: weather=${f.weather}, trackTemp=${f.trackTemperature}°C, airTemp=${f.airTemperature}°C, rain=${f.rainPercentage}%"
    }

    return """
        Track: ${F1Metadata.trackName(packet.trackId.toInt())} (${packet.trackId}), Length: ${packet.trackLength} m
        Formula: ${packet.formula}, Session Type: ${packet.sessionType}
        Total Laps: ${packet.totalLaps}, Duration: ${packet.sessionDuration} s, Time Left: ${packet.sessionTimeLeft} s
        Weather: ${packet.weather}, Track Temp: ${packet.trackTemperature}°C, Air Temp: ${packet.airTemperature}°C
        Pit Speed Limit: ${packet.pitSpeedLimit} km/h, Safety Car Status: ${packet.safetyCarStatus}
        Game Paused: ${packet.gamePaused}, Spectating: ${packet.isSpectating}, Network Game: ${packet.networkGame}
        Forecast Accuracy: ${packet.forecastAccuracy}, AI Difficulty: ${packet.aiDifficulty}
        Pit Window: idealLap=${packet.pitStopWindowIdealLap}, latestLap=${packet.pitStopWindowLatestLap}, rejoinPos=${packet.pitStopRejoinPosition}
        Assists: Steering=${packet.steeringAssist}, Braking=${packet.brakingAssist}, Gearbox=${packet.gearboxAssist}, Pit=${packet.pitAssist}, PitRelease=${packet.pitReleaseAssist}, ERS=${packet.ERSAssist}, DRS=${packet.DRSAssist}
        Racing Line: ${packet.dynamicRacingLine}, Type=${packet.dynamicRacingLineType}
        Game Mode: ${packet.gameMode}, Rule Set: ${packet.ruleSet}, Time of Day: ${packet.timeOfDay}, Session Length: ${packet.sessionLength}
        Units: SpeedLead=${packet.speedUnitsLeadPlayer}, TempLead=${packet.temperatureUnitsLeadPlayer}, SpeedSec=${packet.speedUnitsSecondaryPlayer}, TempSec=${packet.temperatureUnitsSecondaryPlayer}
        Safety Car Periods: ${packet.numSafetyCarPeriods}, VSC Periods: ${packet.numVirtualSafetyCarPeriods}, Red Flags: ${packet.numRedFlagPeriods}
        Equal Car Performance: ${packet.equalCarPerformance}, Recovery Mode: ${packet.recoveryMode}, Flashback Limit: ${packet.flashbackLimit}
        Surface Type: ${packet.surfaceType}, Low Fuel Mode: ${packet.lowFuelMode}, Race Starts: ${packet.raceStarts}
        Tyre Temperature Mode: ${packet.tyreTemperature}, Pit Lane Tyre Sim: ${packet.pitLaneTyreSim}
        Car Damage: ${packet.carDamage}, Damage Rate: ${packet.carDamageRate}
        Collisions: ${packet.collisions}, First Lap Collisions Off: ${packet.collisionsOffForFirstLapOnly}
        MP Unsafe Pit Release: ${packet.mpUnsafePitRelease}, MP Off For Griefing: ${packet.mpOffForGriefing}
        Corner Cutting: ${packet.cornerCuttingStringency}, Parc Ferme: ${packet.parcFermeRules}
        Pit Stop Experience: ${packet.pitStopExperience}, Safety Car Setting: ${packet.safetyCar}, Safety Car Experience: ${packet.safetyCarExperience}
        Formation Lap: ${packet.formationLap}, Formation Lap Experience: ${packet.formationLapExperience}
        Red Flags Setting: ${packet.redFlags}
        Licence Solo: ${packet.affectsLicenceLevelSolo}, Licence MP: ${packet.affectsLicenceLevelMP}
        Weekend Sessions Count: ${packet.numSessionsInWeekend}, Weekend Structure: ${packet.weekendStructure.joinToString()}
        Sector 2 Start: ${packet.sector2LapDistanceStart} m, Sector 3 Start: ${packet.sector3LapDistanceStart} m
        Weather Forecast Samples (first 3):
        $forecasts
    """.trimIndent()
}


// Lap Data (ID 2)
fun formatLapData24(packet: PacketLapData): String {
    val car0 = packet.lapData[packet.header.playerCarIndex.toInt()]

    val sector3Time =
        car0.lastLapTimeInMS.toInt() -
                (car0.sector1TimeMSPart.toInt() + car0.sector2TimeMSPart.toInt())

    return """
        Current Lap: ${car0.currentLapNum}
        Car Position: ${car0.carPosition}
        Last Lap Time: ${car0.lastLapTimeInMS.toDouble() / 1000.0} s
        Current Lap Time: ${car0.currentLapTimeInMS.toDouble() / 1000.0} s
        Sector 1: ${car0.sector1TimeMSPart} ms
        Sector 1 Minutes: ${car0.sector1TimeMinutesPart}
        Sector 2: ${car0.sector2TimeMSPart} ms
        Sector 2 Minutes: ${car0.sector2TimeMinutesPart}
        Sector 3: $sector3Time ms
        Lap Distance: ${car0.lapDistance} m
        Total Distance: ${car0.totalDistance} m
        Pit Status: ${car0.pitStatus} (0=none,1=pitting,2=in pit)
        Num Pit Stops: ${car0.numPitStops}
        Driver Status: ${car0.driverStatus} (0=garage,1=flying,2=in lap,3=out lap,4=on track)
        Result Status: ${car0.resultStatus} (0=invalid,1=inactive,2=active,3=finished,4=DSQ,5=NC,6=retired)
        Penalties: ${car0.penalties} s
        Total Warnings: ${car0.totalWarnings}
        Corner Cutting Warnings: ${car0.cornerCuttingWarnings}
        Unserved Drive-Through Pens: ${car0.numUnservedDriveThroughPens}
        Unserved Stop-Go Pens: ${car0.numUnservedStopGoPens}
        Grid Position: ${car0.gridPosition}
        Pit Lane Timer Active: ${car0.pitLaneTimerActive}
        Pit Lane Time: ${car0.pitLaneTimeInLaneInMS} ms
        Pit Stop Timer: ${car0.pitStopTimerInMS} ms
        Pit Stop Should Serve Pen: ${car0.pitStopShouldServePen}
        Speed Trap Fastest Speed: ${car0.speedTrapFastestSpeed} km/h
        Speed Trap Fastest Lap: ${car0.speedTrapFastestLap}
    """.trimIndent()
}

// Event Data (ID 3)
fun formatEvent24(packet: PacketEventData): String {
    val code = packet.eventStringCode
    val name = F1Metadata.eventStringName(code)
    return "Event: $name ($code)"
}

// Participants (ID 4)
fun formatParticipants24(packet: PacketParticipantsData): String {
    val playerIdx = packet.header.playerCarIndex.toInt()
    val player = packet.participants[playerIdx]

    return """
        Active Cars: ${packet.numActiveCars}
        Driver: ${player.name}
        Race Number: ${player.raceNumber}
        Team: ${F1Metadata.teamName(player.teamId.toInt())} (${player.teamId})
        Nationality: ${F1Metadata.nationalityName(player.nationality.toInt())} (${player.nationality})
        AI Controlled: ${player.aiControlled} (0=Human, 1=AI)
        My Team: ${player.myTeam}
        Driver ID: ${F1Metadata.driverName(player.driverId.toInt())}  (${player.driverId})
        Network ID: ${player.networkId}
        Telemetry: ${player.yourTelemetry} (0=restricted, 1=public)
        Show Online Names: ${player.showOnlineNames}
        Tech Level: ${player.techLevel}
        Platform: ${player.platform} (1=Steam, 3=PlayStation, 4=Xbox, 6=Origin, 255=unknown)
    """.trimIndent()
}

// Car Setups (ID 5)
fun formatCarSetups24(packet: PacketCarSetupData): String {
    val playerIdx = packet.header.playerCarIndex.toInt()
    val setup = packet.carSetups[playerIdx]

    return """
        Aero:
          Front Wing: ${setup.frontWing}
          Rear Wing: ${setup.rearWing}
        Throttle:
          On-Throttle: ${setup.onThrottle}%
          Off-Throttle: ${setup.offThrottle}%
        Suspension:
          Front: ${setup.frontSuspension}, Rear: ${setup.rearSuspension}
          Anti-Roll Bar: Front=${setup.frontAntiRollBar}, Rear=${setup.rearAntiRollBar}
          Height: Front=${setup.frontSuspensionHeight}, Rear=${setup.rearSuspensionHeight}
        Alignment:
          Camber: Front=${setup.frontCamber}, Rear=${setup.rearCamber}
          Toe: Front=${setup.frontToe}, Rear=${setup.rearToe}
        Brakes:
          Pressure: ${setup.brakePressure}%
          Bias: ${setup.brakeBias}%
          Engine Braking: ${setup.engineBraking}%        // ✅ NEW IN 24
        Tyre Pressures:
          Front Left=${setup.frontLeftTyrePressure} psi, Front Right=${setup.frontRightTyrePressure} psi
          Rear Left=${setup.rearLeftTyrePressure} psi, Rear Right=${setup.rearRightTyrePressure} psi
        Misc:
          Ballast: ${setup.ballast}
          Fuel Load: ${setup.fuelLoad} L
          Next Pitstop Front Wing: ${packet.nextFrontWingValue}   // ✅ NEW IN 24
    """.trimIndent()
}

// Car Telemetry (ID 6)
fun formatCarTelemetry24(packet: PacketCarTelemetryData): String {
    val playerIdx = packet.header.playerCarIndex.toInt()
    val car = packet.carTelemetryData[playerIdx]

    return """
        Speed: ${car.speed} km/h
        Throttle: ${(car.throttle * 100).toInt()}%
        Brake: ${(car.brake * 100).toInt()}%
        Steer: ${(car.steer * 100).toInt()}%
        Clutch: ${car.clutch}%
        Gear: ${car.gear}
        RPM: ${car.engineRPM}
        DRS: ${car.drs}
        Rev Lights: ${car.revLightsPercent}% (bitValue=${car.revLightsBitValue})
        Brakes Temp: FL=${car.brakesTemperature[2]}°C, FR=${car.brakesTemperature[3]}°C,
                     RL=${car.brakesTemperature[0]}°C, RR=${car.brakesTemperature[1]}°C
        Tyres Surface Temp: FL=${car.tyresSurfaceTemperature[2]}°C, FR=${car.tyresSurfaceTemperature[3]}°C,
                            RL=${car.tyresSurfaceTemperature[0]}°C, RR=${car.tyresSurfaceTemperature[1]}°C
        Tyres Inner Temp: FL=${car.tyresInnerTemperature[2]}°C, FR=${car.tyresInnerTemperature[3]}°C,
                          RL=${car.tyresInnerTemperature[0]}°C, RR=${car.tyresInnerTemperature[1]}°C
        Tyres Pressure: FL=${car.tyresPressure[2]} psi, FR=${car.tyresPressure[3]} psi,
                        RL=${car.tyresPressure[0]} psi, RR=${car.tyresPressure[1]} psi
        Engine Temp: ${car.engineTemperature}°C
        Surface Types: FL=${car.surfaceType[2]}, FR=${car.surfaceType[3]},
                       RL=${car.surfaceType[0]}, RR=${car.surfaceType[1]}
        MFD Panel Index: ${packet.mfdPanelIndex}, Secondary: ${packet.mfdPanelIndexSecondaryPlayer}
        Suggested Gear: ${packet.suggestedGear}
    """.trimIndent()
}

// Car Status (ID 7)
fun formatCarStatus24(packet: PacketCarStatusData): String {
    val playerIdx = packet.header.playerCarIndex.toInt()
    val car = packet.carStatusData[playerIdx]

    return """
        Controls:
          Traction Control: ${car.tractionControl}
          ABS: ${car.antiLockBrakes}
          Fuel Mix: ${car.fuelMix}
          Front Brake Bias: ${car.frontBrakeBias}%
          Pit Limiter: ${car.pitLimiterStatus}
        
        Fuel:
          In Tank: ${car.fuelInTank} L
          Capacity: ${car.fuelCapacity} L
          Remaining Laps: ${car.fuelRemainingLaps}
        
        Engine:
          Max RPM: ${car.maxRPM}
          Idle RPM: ${car.idleRPM}
          Max Gears: ${car.maxGears}
          Power ICE: ${car.enginePowerICE} kW
          Power MGU-K: ${car.enginePowerMGUK} kW
          Network Paused: ${car.networkPaused}
        
        DRS:
          Allowed: ${car.drsAllowed}
          Activation Distance: ${car.drsActivationDistance} m
        
        Tyres:
          Actual Compound: ${car.actualTyreCompound}
          Visual Compound: ${car.visualTyreCompound}
          Age: ${car.tyresAgeLaps} laps
        
        Flags:
          FIA Vehicle Flags: ${car.vehicleFiaFlags}
        
        ERS:
          Store Energy: ${car.ersStoreEnergy} J
          Deploy Mode: ${car.ersDeployMode}
          Harvested This Lap: MGUK=${car.ersHarvestedThisLapMGUK} J, MGUH=${car.ersHarvestedThisLapMGUH} J
          Deployed This Lap: ${car.ersDeployedThisLap} J
    """.trimIndent()
}

// Final Classification (ID 8)
fun formatFinalClassification24(packet: PacketFinalClassificationData): String {
    val playerIdx = packet.header.playerCarIndex.toInt()
    val car = packet.classificationData.getOrNull(playerIdx)
        ?: return "No final classification data"

    val bestLapSeconds = car.bestLapTimeMs.toDouble() / 1000.0

    return """
        Position: ${car.position}
        Grid Position: ${car.gridPosition}
        Laps Completed: ${car.numLaps}
        Points: ${car.points}
        Pit Stops: ${car.numPitStops}
        Result Status: ${car.resultStatus}
        Best Lap: ${"%.3f".format(bestLapSeconds)} s
        Total Race Time: ${"%.3f".format(car.totalRaceTime)} s
        Penalties: ${car.numPenalties} (Total Time=${car.penaltiesTime} s)
        Tyre Stints (Visual): ${car.tyreStintsVisual.joinToString()}
        Tyre Stints (Actual): ${car.tyreStintsActual.joinToString()}
        Tyre Stints End Lap: ${car.tyreStintsEndLap.joinToString()}
    """.trimIndent()
}

// Lobby Info (ID 9)
fun formatLobbyInfo24(packet: PacketLobbyInfoData): String {
    val headerInfo = "Players in Lobby: ${packet.numPlayers}"
    val roster = packet.lobbyPlayers.mapIndexed { idx, p ->
        "Player $idx → ${p.name} (#${p.carNumber}), " +
                "Team=${p.teamId}, Nationality=${p.nationality}, Platform=${p.platform}, " +
                "AI=${p.aiControlled}, Telemetry=${p.yourTelemetry}, ShowNames=${p.showOnlineNames}, " +
                "TechLevel=${p.techLevel}, Ready=${p.readyStatus}"
    }.joinToString("\n")

    return "$headerInfo\n$roster"
}

// Car Damage (ID 10)
fun formatCarDamage24(packet: PacketCarDamageData): String {
    val playerIdx = packet.header.playerCarIndex.toInt()
    val car = packet.carDamageData.getOrNull(playerIdx)
        ?: return "No car damage data"

    return """
        Tyres Wear: FL=${car.tyresWear[2]}%, FR=${car.tyresWear[3]}%, RL=${car.tyresWear[0]}%, RR=${car.tyresWear[1]}%
        Tyres Damage: FL=${car.tyresDamage[2]}%, FR=${car.tyresDamage[3]}%, RL=${car.tyresDamage[0]}%, RR=${car.tyresDamage[1]}%
        Brakes Damage: FL=${car.brakesDamage[2]}%, FR=${car.brakesDamage[3]}%, RL=${car.brakesDamage[0]}%, RR=${car.brakesDamage[1]}%
        
        Aero:
          Front Left Wing: ${car.frontLeftWingDamage}%
          Front Right Wing: ${car.frontRightWingDamage}%
          Rear Wing: ${car.rearWingDamage}%
        
        Chassis:
          Floor Damage: ${car.floorDamage}%
          Diffuser Damage: ${car.diffuserDamage}%
          Sidepod Damage: ${car.sidepodDamage}%
        
        Faults:
          DRS Fault: ${car.drsFault}
          ERS Fault: ${car.ersFault}
        
        Powertrain:
          Gearbox Damage: ${car.gearBoxDamage}%
          Engine Damage: ${car.engineDamage}%
          Engine ICE Wear: ${car.engineICEWear}%
          Engine MGU-H Wear: ${car.engineMGUHWear}%
          Engine MGU-K Wear: ${car.engineMGUKWear}%
          Engine ES Wear: ${car.engineESWear}%
          Engine CE Wear: ${car.engineCEWear}%
          Engine TC Wear: ${car.engineTCWear}%
          Engine Blown: ${car.engineBlown}
          Engine Seized: ${car.engineSeized}
    """.trimIndent()
}

// Session History (ID 11)
fun formatSessionHistory24(packet: PacketSessionHistoryData): String {
    val lapsSummary = packet.lapHistoryData.take(5).mapIndexed { idx, lap ->
        val lapTimeSec = lap.lapTimeMs.toDouble() / 1000.0
        "Lap ${idx + 1}: ${"%.3f".format(lapTimeSec)}s " +
                "(S1=${lap.sector1TimeMs}ms, S2=${lap.sector2TimeMs}ms, S3=${lap.sector3TimeMs}ms, Valid=${lap.lapValidBitFlags})"
    }.joinToString("\n")

    val tyreStintsSummary = packet.tyreStintsHistoryData.mapIndexed { idx, stint ->
        "Stint ${idx + 1}: EndLap=${stint.endLap}, ActualCompound=${stint.tyreActualCompound}, VisualCompound=${stint.tyreVisualCompound}"
    }.joinToString("\n")

    return """
        Total Laps: ${packet.numLaps}
        Best Lap: Lap ${packet.bestLapTimeLapNum}
        Best Sector 1: Lap ${packet.bestSector1LapNum}
        Best Sector 2: Lap ${packet.bestSector2LapNum}
        Best Sector 3: Lap ${packet.bestSector3LapNum}
        
        Lap History (first 5 laps):
        $lapsSummary
        
        Tyre Stints:
        $tyreStintsSummary
    """.trimIndent()
}

// Tyre Sets (ID 12)
fun formatTyreSets24(packet: PacketTyreSetsData): String {
    val player = packet.tyreSetData[packet.fittedIdx.toInt()]
    return """
        Tyre Set → Compound=${player.actualTyreCompound}
        Wear: ${player.wear}%
        Life Span: ${player.lifeSpan} laps
        Usable Life: ${player.usableLife} laps
    """.trimIndent()
}

// MotionEx (ID ?)
fun formatMotionEx24(packet: PacketMotionExData): String {
    return """
        MotionEx →
        SuspPos FL=${packet.suspensionPosition[2]}
        WheelSpeed RR=${packet.wheelSpeed[1]}
        Front Wheels Angle=${packet.frontWheelsAngle}
        FrontAeroHeight=${packet.frontAeroHeight}
        RearAeroHeight=${packet.rearAeroHeight}
        FrontRollAngle=${packet.frontRollAngle}
        RearRollAngle=${packet.rearRollAngle}
        ChassisYaw=${packet.chassisYaw}
    """.trimIndent()
}

fun formatTimeTrial24(packet: PacketTimeTrialData): String {
    fun fmt(label: String, d: TimeTrialDataSet) =
        "$label → Car=${d.carIdx}, Team=${d.teamId}, Lap=${d.lapTimeInMS} ms, " +
                "S1=${d.sector1TimeInMS}, S2=${d.sector2TimeInMS}, S3=${d.sector3TimeInMS}, " +
                "TC=${d.tractionControl}, GBox=${d.gearboxAssist}, ABS=${d.antiLockBrakes}, " +
                "Equal=${d.equalCarPerformance}, Custom=${d.customSetup}, Valid=${d.valid}"

    return """
        Time Trial Data
        ${fmt("Player Session Best", packet.playerSessionBestDataSet)}
        ${fmt("Personal Best", packet.personalBestDataSet)}
        ${fmt("Rival", packet.rivalDataSet)}
    """.trimIndent()
}
