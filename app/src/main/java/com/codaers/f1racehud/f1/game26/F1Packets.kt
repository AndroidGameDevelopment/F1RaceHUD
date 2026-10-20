package com.codaers.f1racehud.f1.game26
import com.codaers.f1racehud.PacketHeader

// Common header for all packets


// ---------------- Packet 0: Motion ----------------
data class CarMotionData(
    val worldPositionX: Float,
    val worldPositionY: Float,
    val worldPositionZ: Float,
    val worldVelocityX: Float,
    val worldVelocityY: Float,
    val worldVelocityZ: Float,
    val worldForwardDirX: Short,
    val worldForwardDirY: Short,
    val worldForwardDirZ: Short,
    val worldRightDirX: Short,
    val worldRightDirY: Short,
    val worldRightDirZ: Short,
    val gForceLateral: Float,
    val gForceLongitudinal: Float,
    val gForceVertical: Float,
    val yaw: Float,
    val pitch: Float,
    val roll: Float
)

data class PacketMotionData(
    val header: PacketHeader,
    val carMotionData: List<CarMotionData>
)

// ---------------- Packet 1: Session ----------------
// ✅ Correct Kotlin top-level constants
//const val MAX_MARSHAL_ZONES_PER_LAP: Int = 21
//const val MAX_ACTIVE_AERO_ZONES_PER_LAP: Int = 8
//const val MAX_DRS_ZONES_PER_LAP: Int = 4
//const val MAX_WEATHER_FORECAST_SAMPLES: Int = 64
//const val MAX_SESSIONS_IN_WEEKEND: Int = 12
data class MarshalZone(
    val zoneStart: Float,
    val zoneFlag: Byte
)

// New in 26
data class ActiveAeroZone(
    val zoneStart: Float,
    val zoneEnd: Float
)

// New in 26
data class DRSZone(
    val zoneStart: Float,
    val zoneEnd: Float
)

data class WeatherForecastSample(
    val sessionType: UByte,            // 0 = unknown, 1 = P1, 2 = P2, ...
    val timeOffset: UByte,             // minutes ahead
    val weather: UByte,                // 0 = clear, 1 = light cloud, ...
    val trackTemperature: Byte,        // int8
    val trackTemperatureChange: Byte,  // int8 (0 = up, 1 = down, 2 = no change)
    val airTemperature: Byte,          // int8
    val airTemperatureChange: Byte,    // int8
    val rainPercentage: UByte          // 0–100
)

data class PacketSessionData(
    val header: PacketHeader,

    val weather: UByte,
    val trackTemperature: Byte,
    val airTemperature: Byte,
    val totalLaps: UByte,
    val trackLength: UShort,
    val sessionType: UByte,
    val trackId: Byte,
    val formula: UByte,
    val sessionTimeLeft: UShort,
    val sessionDuration: UShort,
    val pitSpeedLimit: UByte,
    val gamePaused: UByte,
    val isSpectating: UByte,
    val spectatorCarIndex: UByte,
    val sliProNativeSupport: UByte,
    val numMarshalZones: UByte,
    val marshalZones: List<MarshalZone>,
    val safetyCarStatus: UByte,
    val networkGame: UByte,
    val numWeatherForecastSamples: UByte,
    val weatherForecastSamples: List<WeatherForecastSample>,
    val forecastAccuracy: UByte,
    val aiDifficulty: UByte,
    val seasonLinkIdentifier: UInt,
    val weekendLinkIdentifier: UInt,
    val sessionLinkIdentifier: UInt,
    val pitStopWindowIdealLap: UByte,
    val pitStopWindowLatestLap: UByte,
    val pitStopRejoinPosition: UByte,
    val steeringAssist: UByte,
    val brakingAssist: UByte,
    val gearboxAssist: UByte,
    val pitAssist: UByte,
    val pitReleaseAssist: UByte,
    @Suppress("PropertyName") val ERSAssist: UByte,
    @Suppress("PropertyName") val DRSAssist: UByte,
    val dynamicRacingLine: UByte,
    val dynamicRacingLineType: UByte,
    val gameMode: UByte,
    val ruleSet: UByte,
    val timeOfDay: UInt,
    val sessionLength: UByte,
    val speedUnitsLeadPlayer: UByte,
    val temperatureUnitsLeadPlayer: UByte,
    val speedUnitsSecondaryPlayer: UByte,
    val temperatureUnitsSecondaryPlayer: UByte,
    val numSafetyCarPeriods: UByte,
    val numVirtualSafetyCarPeriods: UByte,
    val numRedFlagPeriods: UByte,
    val equalCarPerformance: UByte,
    val recoveryMode: UByte,
    val flashbackLimit: UByte,
    val surfaceType: UByte,
    val lowFuelMode: UByte,
    val raceStarts: UByte,
    val tyreTemperature: UByte,
    val pitLaneTyreSim: UByte,
    val carDamage: UByte,
    val carDamageRate: UByte,
    val collisions: UByte,
    val collisionsOffForFirstLapOnly: UByte,
    val mpUnsafePitRelease: UByte,
    val mpOffForGriefing: UByte,
    val cornerCuttingStringency: UByte,
    val parcFermeRules: UByte,
    val pitStopExperience: UByte,
    val safetyCar: UByte,
    val safetyCarExperience: UByte,
    val formationLap: UByte,
    val formationLapExperience: UByte,
    val redFlags: UByte,
    val affectsLicenceLevelSolo: UByte,
    val affectsLicenceLevelMP: UByte,
    val numSessionsInWeekend: UByte,
    val weekendStructure: List<UByte>,
    val sector2LapDistanceStart: Float,
    val sector3LapDistanceStart: Float,
    // Aero and DRZ
    val activeAeroTrackStatus: UByte,               // 0 = Full, 1 = Partial
    val numActiveAeroZonesFull: UByte,              // Number of Active Aero zones to follow
    val activeAeroZonesFull: List<UByte>,           // List of Active Aero zones - max 8
    val numActiveAeroZonesPartial: UByte,           // Number of Active Aero zones to follow
    val activeAeroZonesPartial: List<UByte>,        // List of Active Aero zones - max 8
    val numDRSZones: UByte,                         // Number of DRS zones to follow
    val drsZones: List<UByte>,                      // List of DRS zones - max 4
    val startReactionTime: Float,                    // Driver start reaction time in seconds, 0.0f if assisted starts
    val antiLockBrakesAssist: UByte,                // 0 = off, 1 = on
    val tractionControlAssist: UByte,               // 0 = Off, 1 = Medium, 2 = Full
    val dynamicRacingLineHiVis: UByte,              // 0 = Off, 1 = On
    val dynamicRacingLineColourBlind: UByte,        // 0 = Off, 1 = Protanopia, 2 = Deuteranopia, 3 = Tritanopia
    val recurringRewindPrompt: UByte,               // 0 = Off, 1 = On
)


// ---------------- Packet 2: Lap Data ----------------
data class LapData(
    val lastLapTimeInMS: UInt,
    val currentLapTimeInMS: UInt,
    val sector1TimeMSPart: UShort,
    val sector1TimeMinutesPart: UByte,
    val sector2TimeMSPart: UShort,
    val sector2TimeMinutesPart: UByte,
    val deltaToCarInFrontMSPart: UShort,
    val deltaToCarInFrontMinutesPart: UByte,
    val deltaToRaceLeaderMSPart: UShort,
    val deltaToRaceLeaderMinutesPart: UByte,
    val lapDistance: Float,
    val totalDistance: Float,
    val safetyCarDelta: Float,
    val carPosition: UByte,
    val currentLapNum: UByte,
    val pitStatus: UByte,
    val numPitStops: UByte,
    val sector: UByte,
    val currentLapInvalid: UByte,
    val penalties: UByte,
    val totalWarnings: UByte,
    val cornerCuttingWarnings: UByte,
    val numUnservedDriveThroughPens: UByte,
    val numUnservedStopGoPens: UByte,
    val gridPosition: UByte,
    val driverStatus: UByte,
    val resultStatus: UByte,
    val pitLaneTimerActive: UByte,
    val pitLaneTimeInLaneInMS: UShort,
    val pitStopTimerInMS: UShort,
    val pitStopShouldServePen: UByte,
    val speedTrapFastestSpeed: Float,
    val speedTrapFastestLap: UByte
)

data class PacketLapData(
    val header: PacketHeader,
    val lapData: List<LapData>,             // 22 entries
    val timeTrialPBCarIdx: UByte,           // uint8
    val timeTrialRivalCarIdx: UByte         // uint8
)

// ---------------- Packet 3: Event ----------------
sealed class EventDataDetails {
    data class FastestLap(
        val vehicleIdx: UByte,
        val lapTime: Float
    ) : EventDataDetails()

    data class Retirement(
        val vehicleIdx: UByte,
        val reason: UByte // Added
    ) : EventDataDetails()

    data class DRSDisabled(
        val reason: UByte // Added Entire Class
    ) : EventDataDetails()

    data class TeamMateInPits(
        val vehicleIdx: UByte
    ) : EventDataDetails()

    data class RaceWinner(
        val vehicleIdx: UByte
    ) : EventDataDetails()

    data class Penalty(
        val penaltyType: UByte,
        val infringementType: UByte,
        val vehicleIdx: UByte,
        val otherVehicleIdx: UByte,
        val time: UByte,
        val lapNum: UByte,
        val placesGained: UByte
    ) : EventDataDetails()

    data class SpeedTrap(
        val vehicleIdx: UByte,
        val speed: Float,
        val isOverallFastestInSession: UByte,
        val isDriverFastestInSession: UByte,
        val fastestVehicleIdxInSession: UByte,
        val fastestSpeedInSession: Float
    ) : EventDataDetails()

    data class StartLights(
        val numLights: UByte
    ) : EventDataDetails()

    data class DriveThroughPenaltyServed(
        val vehicleIdx: UByte
    ) : EventDataDetails()

    data class StopGoPenaltyServed(
        val vehicleIdx: UByte,
        val stopTime: Float // Added
    ) : EventDataDetails()

    data class Flashback(
        val flashbackFrameIdentifier: UInt,
        val flashbackSessionTime: Float
    ) : EventDataDetails()

    data class Buttons(
        val buttonStatus: UInt
    ) : EventDataDetails()

    data class Overtake(
        val overtakingVehicleIdx: UByte,
        val beingOvertakenVehicleIdx: UByte
    ) : EventDataDetails()

    data class SafetyCar(
        val safetyCarType: UByte,
        val eventType: UByte
    ) : EventDataDetails()

    data class Collision(
        val vehicle1Idx: UByte,
        val vehicle2Idx: UByte,
        val severity: UByte // Added
    ) : EventDataDetails()
}

data class PacketEventData(
    val header: PacketHeader,
    val eventStringCode: String,
    val eventDetails: EventDataDetails
)


// ---------------- Packet 4: Participants ----------------
data class LiveryColour(
    val red: UByte,
    val green: UByte,
    val blue: UByte
)

data class ParticipantData(
    val aiControlled: UByte,
    val driverId: UByte,
    val networkId: UByte,
    val teamId: UByte,
    val myTeam: UByte,
    val raceNumber: UByte,
    val nationality: UByte,
    val name: String,              // UTF-8, max 32 chars
    val yourTelemetry: UByte,
    val showOnlineNames: UByte,
    val techLevel: UShort,
    val platform: UByte,
    val numColours: UByte,
    val liveryColours: List<LiveryColour> // size = 4
)

data class PacketParticipantsData(
    val header: PacketHeader,
    val numActiveCars: UByte,
    val participants: List<ParticipantData>
)


// ---------------- Packet 5: Car Setups ----------------
data class CarSetupData(
    val frontWing: UByte,              // Front wing aero
    val rearWing: UByte,               // Rear wing aero
    val onThrottle: UByte,             // Differential adjustment on throttle (percentage)
    val offThrottle: UByte,            // Differential adjustment off throttle (percentage)
    val frontCamber: Float,            // Front camber angle (suspension geometry)
    val rearCamber: Float,             // Rear camber angle (suspension geometry)
    val frontToe: Float,               // Front toe angle (suspension geometry)
    val rearToe: Float,                // Rear toe angle (suspension geometry)
    val frontSuspension: UByte,        // Front suspension
    val rearSuspension: UByte,         // Rear suspension
    val frontAntiRollBar: UByte,       // Front anti-roll bar
    val rearAntiRollBar: UByte,        // Front anti-roll bar
    val frontSuspensionHeight: UByte,  // Front ride height
    val rearSuspensionHeight: UByte,   // Rear ride height
    val brakePressure: UByte,          // Brake pressure (percentage)
    val brakeBias: UByte,              // Brake bias (percentage)
    val engineBraking: UByte,          // Engine braking (percentage)  ✅ NEW IN 24
    val rearLeftTyrePressure: Float,   // Rear left tyre pressure (PSI)
    val rearRightTyrePressure: Float,  // Rear right tyre pressure (PSI)
    val frontLeftTyrePressure: Float,  // Front left tyre pressure (PSI)
    val frontRightTyrePressure: Float, // Front right tyre pressure (PSI)
    val ballast: UByte,                // Ballast
    val fuelLoad: Float                // Fuel load
)

data class PacketCarSetupData(
    val header: PacketHeader,
    val carSetups: List<CarSetupData>,
    val nextFrontWingValue: Float      // Value of front wing after next pit stop - player only
)


// ---------------- Packet 6: Car Telemetry ----------------
data class CarTelemetryData(
    val speed: UShort,                     // uint16
    val throttle: Float,                   // float
    val steer: Float,                      // float
    val brake: Float,                      // float
    val clutch: UByte,                     // uint8
    val gear: Byte,                        // int8
    val engineRPM: UShort,                 // uint16
    val drs: UByte,                        // uint8
    val revLightsPercent: UByte,           // uint8
    val revLightsBitValue: UShort,         // uint16
    val brakesTemperature: List<UShort>,   // 4 x uint16
    val tyresSurfaceTemperature: List<UByte>, // 4 x uint8
    val tyresInnerTemperature: List<UByte>,   // 4 x uint8
    val engineTemperature: UShort,         // uint16
    val tyresPressure: List<Float>,        // 4 x float
    val surfaceType: List<UByte>           // 4 x uint8
)

data class PacketCarTelemetryData(
    val header: PacketHeader,
    val carTelemetryData: List<CarTelemetryData>,
    val mfdPanelIndex: UByte,
    val mfdPanelIndexSecondaryPlayer: UByte,
    val suggestedGear: Byte
)

// ---------------- Packet 7: Car Status ----------------
data class CarStatusData(
    val tractionControl: UByte,
    val antiLockBrakes: UByte,
    val fuelMix: UByte,
    val frontBrakeBias: UByte,
    val pitLimiterStatus: UByte,
    val fuelInTank: Float,
    val fuelCapacity: Float,
    val fuelRemainingLaps: Float,
    val maxRPM: UShort,
    val idleRPM: UShort,
    val maxGears: UByte,
    val drsAllowed: UByte,
    val drsActivationDistance: UShort,
    val actualTyreCompound: UByte,
    val visualTyreCompound: UByte,
    val tyresAgeLaps: UByte,
    val vehicleFiaFlags: Byte, // signed int8
    val enginePowerICE: Float,
    val enginePowerMGUK: Float,
    val ersStoreEnergy: Float,
    val ersDeployMode: UByte,
    val ersHarvestedThisLapMGUK: Float,
    val ersHarvestedThisLapMGUH: Float,
    val ersDeployedThisLap: Float,
    val networkPaused: UByte
)

data class PacketCarStatusData(
    val header: PacketHeader,
    val carStatusData: List<CarStatusData>
)

// ---------------- Packet 8: Final Classification ----------------
data class FinalClassificationData(
    val position: UByte,
    val numLaps: UByte,
    val gridPosition: UByte,
    val points: UByte,
    val numPitStops: UByte,
    val resultStatus: UByte,
    val resultReason: UByte,          // ✅ NEW in F1 26
    val bestLapTimeMs: UInt,
    val totalRaceTime: Double,
    val penaltiesTime: UByte,
    val numPenalties: UByte,
    val numTyreStints: UByte,
    val tyreStintsActual: List<UByte>,   // always 8 entries
    val tyreStintsVisual: List<UByte>,   // always 8 entries
    val tyreStintsEndLap: List<UByte>    // always 8 entries
)

data class PacketFinalClassificationData(
    val header: PacketHeader,
    val numCars: UByte,
    val classificationData: List<FinalClassificationData>
)

// ---------------- Packet 9: Lobby Info ----------------
data class LobbyInfoData(
    val aiControlled: UByte,      // Whether the vehicle is AI (1) or Human (0) controlled
    val teamId: UByte,            // Team id (255 if no team currently selected)
    val nationality: UByte,       // Nationality of the driver
    val platform: UByte,          // 1=Steam, 3=PlayStation, 4=Xbox, 6=Origin, 255=unknown
    val name: String,             // UTF-8 string, max 32 chars
    val carNumber: UByte,         // Car number of the player
    val yourTelemetry: UByte,     // The player's UDP setting, 0 = restricted, 1 = public
    val showOnlineNames: UByte,   // The player's show online names setting, 0 = off, 1 = on
    val techLevel: UShort,        // F1 World tech level
    val readyStatus: UByte        // 0 = not ready, 1 = ready, 2 = spectating
)

data class PacketLobbyInfoData(
    val header: PacketHeader,
    val numPlayers: UByte,
    val lobbyPlayers: List<LobbyInfoData>
)

// ---------------- Packet 10: Car Damage ----------------
data class CarDamageData(
    val tyresWear: List<Float>,          // Tyre wear (percentage)
    val tyresDamage: List<UByte>,        // Tyre damage (percentage)
    val brakesDamage: List<UByte>,       // Brakes damage (percentage)
    val tyreBlisters: List<UByte>,       // Tyre blisters value (percentage)
    val frontLeftWingDamage: UByte,      // Front left wing damage (percentage)
    val frontRightWingDamage: UByte,     // Front right wing damage (percentage)
    val rearWingDamage: UByte,           // Rear wing damage (percentage)
    val floorDamage: UByte,              // Floor damage (percentage)
    val diffuserDamage: UByte,           // Diffuser damage (percentage)
    val sidepodDamage: UByte,            // Sidepod damage (percentage)
    val drsFault: UByte,                 // Indicator for DRS fault, 0 = OK, 1 = fault
    val ersFault: UByte,                 // Indicator for ERS fault, 0 = OK, 1 = fault
    val gearBoxDamage: UByte,            // Gear box damage (percentage)
    val engineDamage: UByte,             // Engine damage (percentage)
    val engineMGUHWear: UByte,           // Engine wear MGU-H (percentage)
    val engineESWear: UByte,             // Engine wear ES (percentage)
    val engineCEWear: UByte,             // Engine wear CE (percentage)
    val engineICEWear: UByte,            // Engine wear ICE (percentage)
    val engineMGUKWear: UByte,           // Engine wear MGU-K (percentage)
    val engineTCWear: UByte,             // Engine wear TC (percentage)
    val engineBlown: UByte,              // Engine blown, 0 = OK, 1 = fault
    val engineSeized: UByte              // Engine seized, 0 = OK, 1 = fault
)

data class PacketCarDamageData(
    val header: PacketHeader,            // Header
    val carDamageData: List<CarDamageData>
)

// ---------------- Packet 11: Session History ----------------
data class LapHistoryData(
    val lapTimeMs: UInt,             // uint32
    val sector1TimeMs: UShort,       // uint16
    val sector1TimeMinutes: UByte,   // uint8
    val sector2TimeMs: UShort,       // uint16
    val sector2TimeMinutes: UByte,   // uint8
    val sector3TimeMs: UShort,       // uint16
    val sector3TimeMinutes: UByte,   // uint8
    val lapValidBitFlags: UByte      // uint8
)

data class PacketSessionHistoryData(
    val header: PacketHeader,
    val carIndex: UByte,
    val numLaps: UByte,
    val numTyreStints: UByte,
    val bestLapTimeLapNum: UByte,
    val bestSector1LapNum: UByte,
    val bestSector2LapNum: UByte,
    val bestSector3LapNum: UByte,
    val lapHistoryData: List<LapHistoryData>,       // up to 100
    val tyreStintsHistoryData: List<TyreStintHistoryData> // up to 8
)

// ---------------- Packet 12: Extended tyre set data ----------------
data class TyreStintHistoryData(
    val endLap: UByte,              // uint8
    val tyreActualCompound: UByte,  // uint8
    val tyreVisualCompound: UByte   // uint8
)

data class TyreSetData(
    val actualTyreCompound: UByte,     // uint8
    val visualTyreCompound: UByte,     // uint8
    val wear: UByte,                   // uint8
    val available: UByte,              // uint8
    val recommendedSession: UByte,     // uint8
    val lifeSpan: UByte,               // uint8
    val usableLife: UByte,             // uint8
    val lapDeltaTime: Short,           // int16
    val fitted: UByte                  // uint8
)

data class PacketTyreSetsData(
    val header: PacketHeader,
    val carIdx: UByte,
    val tyreSetData: List<TyreSetData>, // always 20 entries
    val fittedIdx: UByte
)

// ---------------- Packet 13: Extended motion data for player car ----------------

data class PacketMotionExData(
    val header: PacketHeader,

    val suspensionPosition: List<Float>,      // 4 floats RL, RR, FL, FR
    val suspensionVelocity: List<Float>,      // 4 floats RL, RR, FL, FR
    val suspensionAcceleration: List<Float>,  // 4 floats RL, RR, FL, FR
    val wheelSpeed: List<Float>,              // 4 floats
    val wheelSlipRatio: List<Float>,          // 4 floats
    val wheelSlipAngle: List<Float>,          // 4 floats
    val wheelLatForce: List<Float>,           // 4 floats
    val wheelLongForce: List<Float>,          // 4 floats

    val heightOfCOGAboveGround: Float,
    val localVelocityX: Float,
    val localVelocityY: Float,
    val localVelocityZ: Float,

    val angularVelocityX: Float,
    val angularVelocityY: Float,
    val angularVelocityZ: Float,

    val angularAccelerationX: Float,
    val angularAccelerationY: Float,
    val angularAccelerationZ: Float,

    val frontWheelsAngle: Float,

    val wheelVertForce: List<Float>,          // 4 floats

    val frontAeroHeight: Float,               // NEW in 24
    val rearAeroHeight: Float,                // NEW in 24
    val frontRollAngle: Float,                // NEW in 24
    val rearRollAngle: Float,                 // NEW in 24
    val chassisYaw: Float,                    // NEW in 24

    val chassisPitch: Float,                  // NEW in 25
    val wheelCamber: List<Float>,             // NEW in 25, 4 floats
    val wheelCamberGain: List<Float>          // NEW in 25, 4 floats
)

// ---------------- Packet 14: Time Trial specific data ----------------
data class TimeTrialDataSet(
    val carIdx: UByte,                 // Index of the car this data relates to (uint8)
    val teamId: UByte,                 // Team id - see appendix (uint8)
    val lapTimeInMS: UInt,             // Lap time in milliseconds (uint32)
    val sector1TimeInMS: UInt,         // Sector 1 time in milliseconds (uint32)
    val sector2TimeInMS: UInt,         // Sector 2 time in milliseconds (uint32)
    val sector3TimeInMS: UInt,         // Sector 3 time in milliseconds (uint32)
    val tractionControl: UByte,        // 0 = off, 1 = medium, 2 = full (uint8)
    val gearboxAssist: UByte,          // 1 = manual, 2 = manual+suggested, 3 = auto (uint8)
    val antiLockBrakes: UByte,         // 0 = off, 1 = on (uint8)
    val equalCarPerformance: UByte,    // 0 = Realistic, 1 = Equal (uint8)
    val customSetup: UByte,            // 0 = No, 1 = Yes (uint8)
    val valid: UByte                   // 0 = invalid lap, 1 = valid lap (uint8)
)

data class PacketTimeTrialData(
    val header: PacketHeader,                  // Packet header

    val playerSessionBestDataSet: TimeTrialDataSet,   // Player session best data set
    val personalBestDataSet: TimeTrialDataSet,        // Player personal best data set
    val rivalDataSet: TimeTrialDataSet                // Rival data set
)

// ---------------- Packet 15: Lap positions on each lap so a chart can be constructed ----------------

data class PacketLapPositionsData(
    val header: PacketHeader,                     // Header
    val numLaps: UByte,                           // Number of laps in the data
    val lapStart: UByte,                          // Starting lap index (0-based)
    val positionForVehicleIdx: List<List<UByte>>  // 50 x 22 matrix, 0 = no record
)

// new in 2026
// ---------------- Packet 16: Car Telemetry 2 ----------------
data class CarTelemetry2Data(
    val activeAeroMode: UByte,                   // 0 = Corner mode, 1 = Straight mode
    val activeAeroAvailable: UByte,              // 0 = not available, 1 = available
    val activeAeroActivationDistance: UShort,    // 0 = Active aero not available, non-zero - Active aero will be available in [X] metres
    val overtakeAvailable: UByte,                // 0 = not available, 1 = available
    val overtakeActive: UByte,                   // 0 = not active, 1 = active
    val overtakeActivationDistance: UShort,      // 0 = Overtake Mode not available, non-zero - Overtake Mode will be available in [X] metres
    val regulations2026: UByte,                  // 0 = vehicle conforms to pre-2026, 1 = 2026 regulations applicable
    val drivingWrongWay: UByte,                  // Whether the car is driving the wrong way
)

data class PacketCarTelemetry2Data(
    val header: PacketHeader,
    val carTelemetry2Data: List<CarTelemetry2Data> // data for all cars on track
)
