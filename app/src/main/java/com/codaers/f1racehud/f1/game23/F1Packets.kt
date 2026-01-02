package com.codaers.f1racehud.f1.game23
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
data class MarshalZone(
    val zoneStart: Float,
    val zoneFlag: Byte
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
    val marshalZones: List<MarshalZone>,   // up to 21

    val safetyCarStatus: UByte,
    val networkGame: UByte,

    val numWeatherForecastSamples: UByte,
    val weatherForecastSamples: List<WeatherForecastSample>, // up to 56

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
    val numRedFlagPeriods: UByte
)

// ---------------- Packet 2: Lap Data ----------------
data class LapData(
    val lastLapTimeInMS: UInt,              // Last lap time in milliseconds (uint32)
    val currentLapTimeInMS: UInt,           // Current time around the lap in milliseconds (uint32)
    val sector1TimeInMS: UShort,            // Sector 1 time in milliseconds (uint16)
    val sector1TimeMinutes: UByte,          // Sector 1 whole minute part (uint8)
    val sector2TimeInMS: UShort,            // Sector 2 time in milliseconds (uint16)
    val sector2TimeMinutes: UByte,          // Sector 2 whole minute part (uint8)
    val deltaToCarInFrontInMS: UShort,      // Time delta to car in front in milliseconds (uint16)
    val deltaToRaceLeaderInMS: UShort,      // Time delta to race leader in milliseconds (uint16)
    val lapDistance: Float,                 // Distance around current lap in metres (float), Can be negative if line not yet crossed
    val totalDistance: Float,               // Total distance travelled in session in metres (float) - Can be negative if line not yet crossed
    val safetyCarDelta: Float,              // Safety car delta in seconds (float)
    val carPosition: UByte,                 // Car race position (uint8)
    val currentLapNum: UByte,               // Current lap number (uint8)
    val pitStatus: UByte,                   // Pit status: 0 = none, 1 = pitting, 2 = in pit area (uint8)
    val numPitStops: UByte,                 // Number of pit stops taken (uint8)
    val sector: UByte,                      // 0 = sector1, 1 = sector2, 2 = sector3 (uint8)
    val currentLapInvalid: UByte,           // Current lap invalid: 0 = valid, 1 = invalid (uint8)
    val penalties: UByte,                   // Accumulated time penalties in seconds (uint8)
    val totalWarnings: UByte,               // Accumulated number of warnings issued (uint8)
    val cornerCuttingWarnings: UByte,       // Accumulated corner cutting warnings (uint8)
    val numUnservedDriveThroughPens: UByte, // Number of unserved drive‑through penalties (uint8)
    val numUnservedStopGoPens: UByte,       // Number of unserved stop‑go penalties (uint8)
    val gridPosition: UByte,                // Grid position the vehicle started in (uint8)
    val driverStatus: UByte,                // Driver status (uint8): // 0 = in garage, 1 = flying lap, 2 = in lap, 3 = out lap, 4 = on track
    val resultStatus: UByte,                // Result status (uint8): // 0 = invalid, 1 = inactive, 2 = active, 3 = finished, 4 = did not finish, 5 = disqualified, 6 = not classified, 7 = retired
    val pitLaneTimerActive: UByte,          // Pit lane timing active: 0 = inactive, 1 = active (uint8)
    val pitLaneTimeInLaneInMS: UShort,      // Time spent in pit lane in ms (uint16)
    val pitStopTimerInMS: UShort,           // Time of actual pit stop in ms (uint16)
    val pitStopShouldServePen: UByte        // Whether car should serve a penalty at this stop (uint8)
)

data class PacketLapData(
    val header: PacketHeader,               // Packet header
    val lapData: List<LapData>,             // Lap data for all 22 cars
    val timeTrialPBCarIdx: UByte,           // Personal Best car index in Time Trial (255 = invalid)
    val timeTrialRivalCarIdx: UByte         // Rival car index in Time Trial (255 = invalid)
)

// ---------------- Packet 3: Event ----------------
data class PacketEventData(
    val header: PacketHeader,
    val eventStringCode: String
)

// ---------------- Packet 4: Participants ----------------
data class ParticipantData(
    val aiControlled: UByte,     // 0 = Human, 1 = AI
    val driverId: UByte,         // Driver ID (255 if network human)
    val networkId: UByte,        // Unique network ID
    val teamId: UByte,           // Team ID
    val myTeam: UByte,           // 1 = My Team, 0 = otherwise
    val raceNumber: UByte,       // Car race number
    val nationality: UByte,      // Driver nationality
    val name: String,            // UTF-8 string, max 48 chars
    val yourTelemetry: UByte,    // 0 = restricted, 1 = public
    val showOnlineNames: UByte,  // 0 = off, 1 = on
    val platform: UByte          // 1=Steam, 3=PlayStation, 4=Xbox, 6=Origin, 255=unknown
)

data class PacketParticipantsData(
    val header: PacketHeader,
    val numActiveCars: UByte,
    val participants: List<ParticipantData> // 22 entries
)


// ---------------- Packet 5: Car Setups ----------------
data class CarSetupData(
    val frontWing: UByte,
    val rearWing: UByte,
    val onThrottle: UByte,
    val offThrottle: UByte,
    val frontCamber: Float,
    val rearCamber: Float,
    val frontToe: Float,
    val rearToe: Float,
    val frontSuspension: UByte,
    val rearSuspension: UByte,
    val frontAntiRollBar: UByte,
    val rearAntiRollBar: UByte,
    val frontSuspensionHeight: UByte,
    val rearSuspensionHeight: UByte,
    val brakePressure: UByte,
    val brakeBias: UByte,
    val rearLeftTyrePressure: Float,
    val rearRightTyrePressure: Float,
    val frontLeftTyrePressure: Float,
    val frontRightTyrePressure: Float,
    val ballast: UByte,
    val fuelLoad: Float
)

data class PacketCarSetupData(
    val header: PacketHeader,
    val carSetups: List<CarSetupData>
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
    val aiControlled: UByte,
    val teamId: UByte,
    val nationality: UByte,
    val platform: UByte,      // <-- add this
    val name: String,
    val carNumber: UByte,
    val readyStatus: UByte
)

data class PacketLobbyInfoData(
    val header: PacketHeader,
    val numPlayers: UByte,
    val lobbyPlayers: List<LobbyInfoData>
)

// ---------------- Packet 10: Car Damage ----------------
data class CarDamageData(
    val tyresWear: List<Float>,          // 4 floats
    val tyresDamage: List<UByte>,        // 4 uint8
    val brakesDamage: List<UByte>,       // 4 uint8
    val frontLeftWingDamage: UByte,
    val frontRightWingDamage: UByte,
    val rearWingDamage: UByte,
    val floorDamage: UByte,
    val diffuserDamage: UByte,
    val sidepodDamage: UByte,
    val drsFault: UByte,
    val ersFault: UByte,                 // <-- missing before
    val gearBoxDamage: UByte,
    val engineDamage: UByte,
    val engineMGUHWear: UByte,
    val engineESWear: UByte,
    val engineCEWear: UByte,
    val engineICEWear: UByte,
    val engineMGUKWear: UByte,
    val engineTCWear: UByte,
    val engineBlown: UByte,              // <-- missing before
    val engineSeized: UByte              // <-- missing before
)

data class PacketCarDamageData(
    val header: PacketHeader,
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

data class TyreStintHistoryData(
    val endLap: UByte,              // uint8
    val tyreActualCompound: UByte,  // uint8
    val tyreVisualCompound: UByte   // uint8
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

    val wheelVertForce: List<Float>           // 4 floats
)