@file:Suppress("SpellCheckingInspection")

package com.codaers.f1racehud.screens

import androidx.compose.ui.graphics.Color
import java.util.Locale

private const val MAX_ERS_J = 400000f
private const val ERS_SCALE = 10f

fun applyGraphicsPacket(
    packet: Any?,

    setTotalLaps: (Int) -> Unit,
    setLapTimeMs: (Int) -> Unit,
    setDeltaCarInFrontMS: (Float?) -> Unit,
    setPlayerPosition: (Int) -> Unit,
    setCurrentLap: (Int) -> Unit,
    setPenalties: (Int) -> Unit,
    setTotalWarnings: (Int) -> Unit,
    setCornerCuttingWarnings: (Int) -> Unit,
    setNumUnservedDriveThroughPens: (Int) -> Unit,
    setNumUnservedStopGoPens: (Int) -> Unit,

    setGear: (Int) -> Unit,
    setDrs: (Int) -> Unit,
    setDrsAllowed: (Int) -> Unit,
    setDrsComing: (Int) -> Unit,
    setErsPercent: (Int) -> Unit,
    setErsMode: (Int) -> Unit,
    setFuelRemainingLaps: (Float) -> Unit,

    setFlWear: (Int) -> Unit,
    setFrWear: (Int) -> Unit,
    setRlWear: (Int) -> Unit,
    setRrWear: (Int) -> Unit,

    setFlDamage: (Int) -> Unit,
    setFrDamage: (Int) -> Unit,
    setRlDamage: (Int) -> Unit,
    setRrDamage: (Int) -> Unit,

    setFlWing: (Int) -> Unit,
    setFrWing: (Int) -> Unit,
    setRearWing: (Int) -> Unit,

    setFloorDamage: (Int) -> Unit,
    setDiffuserDamage: (Int) -> Unit,
    setSidepodDamage: (Int) -> Unit,
) {
    when (packet) {

        // ============================================================
        // SESSION DATA (Total Laps)
        // ============================================================

        is com.codaers.f1racehud.f1.game23.PacketSessionData ->
            setTotalLaps(packet.totalLaps.toInt())

        is com.codaers.f1racehud.f1.game24.PacketSessionData ->
            setTotalLaps(packet.totalLaps.toInt())

        is com.codaers.f1racehud.f1.game25.PacketSessionData ->
            setTotalLaps(packet.totalLaps.toInt())


        // ============================================================
        // LAP DATA
        // ============================================================

        is com.codaers.f1racehud.f1.game23.PacketLapData -> {
            val car = packet.lapData[packet.header.playerCarIndex.toInt()]
            setLapTimeMs(car.currentLapTimeInMS.toInt())

            val rawDelta = car.deltaToCarInFrontInMS.toInt()
            setDeltaCarInFrontMS(if (rawDelta == 65535) null else rawDelta / 1000f)

            setPlayerPosition(car.carPosition.toInt())
            setCurrentLap(car.currentLapNum.toInt())
            setPenalties(car.penalties.toInt())
            setTotalWarnings(car.totalWarnings.toInt())
            setCornerCuttingWarnings(car.cornerCuttingWarnings.toInt())
            setNumUnservedDriveThroughPens(car.numUnservedDriveThroughPens.toInt())
            setNumUnservedStopGoPens(car.numUnservedStopGoPens.toInt())
        }

        is com.codaers.f1racehud.f1.game24.PacketLapData -> {
            val car = packet.lapData[packet.header.playerCarIndex.toInt()]
            setLapTimeMs(car.currentLapTimeInMS.toInt())

            val rawDelta = car.deltaToCarInFrontMSPart.toInt()
            setDeltaCarInFrontMS(if (rawDelta == 65535) null else rawDelta / 1000f)

            setPlayerPosition(car.carPosition.toInt())
            setCurrentLap(car.currentLapNum.toInt())
            setPenalties(car.penalties.toInt())
            setTotalWarnings(car.totalWarnings.toInt())
            setCornerCuttingWarnings(car.cornerCuttingWarnings.toInt())
            setNumUnservedDriveThroughPens(car.numUnservedDriveThroughPens.toInt())
            setNumUnservedStopGoPens(car.numUnservedStopGoPens.toInt())
        }

        is com.codaers.f1racehud.f1.game25.PacketLapData -> {
            val car = packet.lapData[packet.header.playerCarIndex.toInt()]
            setLapTimeMs(car.currentLapTimeInMS.toInt())

            val rawDelta = car.deltaToCarInFrontMSPart.toInt()
            setDeltaCarInFrontMS(if (rawDelta == 65535) null else rawDelta / 1000f)

            setPlayerPosition(car.carPosition.toInt())
            setCurrentLap(car.currentLapNum.toInt())
            setPenalties(car.penalties.toInt())
            setTotalWarnings(car.totalWarnings.toInt())
            setCornerCuttingWarnings(car.cornerCuttingWarnings.toInt())
            setNumUnservedDriveThroughPens(car.numUnservedDriveThroughPens.toInt())
            setNumUnservedStopGoPens(car.numUnservedStopGoPens.toInt())
        }


        // ============================================================
        // TELEMETRY
        // ============================================================

        is com.codaers.f1racehud.f1.game23.PacketCarTelemetryData -> {
            val car = packet.carTelemetryData[packet.header.playerCarIndex.toInt()]
            setGear(car.gear.toInt())
            setDrs(car.drs.toInt())
        }

        is com.codaers.f1racehud.f1.game24.PacketCarTelemetryData -> {
            val car = packet.carTelemetryData[packet.header.playerCarIndex.toInt()]
            setGear(car.gear.toInt())
            setDrs(car.drs.toInt())
        }

        is com.codaers.f1racehud.f1.game25.PacketCarTelemetryData -> {
            val car = packet.carTelemetryData[packet.header.playerCarIndex.toInt()]
            setGear(car.gear.toInt())
            setDrs(car.drs.toInt())
        }


        // ============================================================
        // STATUS
        // ============================================================

        is com.codaers.f1racehud.f1.game23.PacketCarStatusData -> {
            val car = packet.carStatusData[packet.header.playerCarIndex.toInt()]

            setDrsAllowed(car.drsAllowed.toInt())
            setDrsComing(car.drsActivationDistance.toInt())

            val ersJoules = car.ersStoreEnergy / ERS_SCALE
            setErsPercent(((ersJoules / MAX_ERS_J) * 100f).toInt().coerceIn(0, 100))

            setErsMode(car.ersDeployMode.toInt())
            setFuelRemainingLaps(car.fuelRemainingLaps)
        }

        is com.codaers.f1racehud.f1.game24.PacketCarStatusData -> {
            val car = packet.carStatusData[packet.header.playerCarIndex.toInt()]

            setDrsAllowed(car.drsAllowed.toInt())
            setDrsComing(car.drsActivationDistance.toInt())

            val ersJoules = car.ersStoreEnergy / ERS_SCALE
            setErsPercent(((ersJoules / MAX_ERS_J) * 100f).toInt().coerceIn(0, 100))

            setErsMode(car.ersDeployMode.toInt())
            setFuelRemainingLaps(car.fuelRemainingLaps)
        }

        is com.codaers.f1racehud.f1.game25.PacketCarStatusData -> {
            val car = packet.carStatusData[packet.header.playerCarIndex.toInt()]

            setDrsAllowed(car.drsAllowed.toInt())
            setDrsComing(car.drsActivationDistance.toInt())

            val ersJoules = car.ersStoreEnergy / ERS_SCALE
            setErsPercent(((ersJoules / MAX_ERS_J) * 100f).toInt().coerceIn(0, 100))

            setErsMode(car.ersDeployMode.toInt())
            setFuelRemainingLaps(car.fuelRemainingLaps)
        }


        // ============================================================
        // DAMAGE
        // ============================================================

        is com.codaers.f1racehud.f1.game23.PacketCarDamageData -> {
            val car = packet.carDamageData[packet.header.playerCarIndex.toInt()]

            setFlWear(car.tyresWear[2].toInt())
            setFrWear(car.tyresWear[3].toInt())
            setRlWear(car.tyresWear[0].toInt())
            setRrWear(car.tyresWear[1].toInt())

            setFlDamage(car.tyresDamage[2].toInt())
            setFrDamage(car.tyresDamage[3].toInt())
            setRlDamage(car.tyresDamage[0].toInt())
            setRrDamage(car.tyresDamage[1].toInt())

            setFlWing(car.frontLeftWingDamage.toInt())
            setFrWing(car.frontRightWingDamage.toInt())
            setRearWing(car.rearWingDamage.toInt())

            setFloorDamage(car.floorDamage.toInt())
            setDiffuserDamage(car.diffuserDamage.toInt())
            setSidepodDamage(car.sidepodDamage.toInt())
        }

        is com.codaers.f1racehud.f1.game24.PacketCarDamageData -> {
            val car = packet.carDamageData[packet.header.playerCarIndex.toInt()]

            setFlWear(car.tyresWear[2].toInt())
            setFrWear(car.tyresWear[3].toInt())
            setRlWear(car.tyresWear[0].toInt())
            setRrWear(car.tyresWear[1].toInt())

            setFlDamage(car.tyresDamage[2].toInt())
            setFrDamage(car.tyresDamage[3].toInt())
            setRlDamage(car.tyresDamage[0].toInt())
            setRrDamage(car.tyresDamage[1].toInt())

            setFlWing(car.frontLeftWingDamage.toInt())
            setFrWing(car.frontRightWingDamage.toInt())
            setRearWing(car.rearWingDamage.toInt())

            setFloorDamage(car.floorDamage.toInt())
            setDiffuserDamage(car.diffuserDamage.toInt())
            setSidepodDamage(car.sidepodDamage.toInt())
        }

        is com.codaers.f1racehud.f1.game25.PacketCarDamageData -> {
            val car = packet.carDamageData[packet.header.playerCarIndex.toInt()]

            setFlWear(car.tyresWear[2].toInt())
            setFrWear(car.tyresWear[3].toInt())
            setRlWear(car.tyresWear[0].toInt())
            setRrWear(car.tyresWear[1].toInt())

            setFlDamage(car.tyresDamage[2].toInt())
            setFrDamage(car.tyresDamage[3].toInt())
            setRlDamage(car.tyresDamage[0].toInt())
            setRrDamage(car.tyresDamage[1].toInt())

            setFlWing(car.frontLeftWingDamage.toInt())
            setFrWing(car.frontRightWingDamage.toInt())
            setRearWing(car.rearWingDamage.toInt())

            setFloorDamage(car.floorDamage.toInt())
            setDiffuserDamage(car.diffuserDamage.toInt())
            setSidepodDamage(car.sidepodDamage.toInt())
        }
    }
}

fun damageColor(value: Int): Color {
    return when {
        value <= 20 -> Color(0xFF006400)     // Green
        //value <= 80 -> Color(0xFFFFD700)          // Yellow
        //value <= 80 -> Color(0xFFFFF27A)          // Light Yellow
        value <= 80 -> Color(0xFFFF8C00)     // Dark Orange
        else -> Color.Red                           // Red
    }
}

fun damageWingsColor(value: Int): Color {
    return when {
        value <= 1 -> Color(0xFF006400)     // Green
        value <= 20 -> Color(0xFFFFF27A)     // Light Yellow
        value <= 80 -> Color(0xFFFF8C00)     // Dark Orange
        else -> Color.Red                           // Red
    }
}

fun tyreValue(wear: Int, damage: Int): Int {
    return if (damage > 0) damage else wear
}

fun tyreColor(wear: Int, damage: Int): Color {
    val v = if (damage > 0) damage else wear
    return when {
        v <= 20 -> Color(0xFF006400)   // Green
        v <= 80 -> Color(0xFFFFD700)   // Yellow
        else -> Color.Red                     // Red
    }
}

fun ersModeName(mode: Int): String = when (mode) {
    0 -> "NONE"
    1 -> "MEDIUM"
    2 -> "HOTLAP"
    3 -> "OVERTAKE"
    else -> "UNK"
}

fun formatLap(ms: Int): String {
    if (ms <= 0) return "--:--:---"
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    val millis = ms % 1000
    return String.format(Locale.US, "%02d:%02d:%03d", minutes, seconds, millis)
}