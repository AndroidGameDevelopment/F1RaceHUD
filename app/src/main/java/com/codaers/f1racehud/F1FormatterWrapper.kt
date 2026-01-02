package com.codaers.f1racehud

import com.codaers.f1racehud.f1.game23.F1Formatters as F1Formatters23
import com.codaers.f1racehud.f1.game24.F1Formatters as F1Formatters24
import com.codaers.f1racehud.f1.game25.F1Formatters as F1Formatters25

object F1FormatterWrapper {

    fun formatMotion(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatMotion(packet as com.codaers.f1racehud.f1.game23.PacketMotionData)
            2024 -> F1Formatters24.formatMotion(packet as com.codaers.f1racehud.f1.game24.PacketMotionData)
            2025 -> F1Formatters25.formatMotion(packet as com.codaers.f1racehud.f1.game25.PacketMotionData)
            else -> null
        }

    fun formatSession(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatSession(packet as com.codaers.f1racehud.f1.game23.PacketSessionData)
            2024 -> F1Formatters24.formatSession(packet as com.codaers.f1racehud.f1.game24.PacketSessionData)
            2025 -> F1Formatters25.formatSession(packet as com.codaers.f1racehud.f1.game25.PacketSessionData)
            else -> null
        }

    fun formatLapData(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatLapData(packet as com.codaers.f1racehud.f1.game23.PacketLapData)
            2024 -> F1Formatters24.formatLapData(packet as com.codaers.f1racehud.f1.game24.PacketLapData)
            2025 -> F1Formatters25.formatLapData(packet as com.codaers.f1racehud.f1.game25.PacketLapData)
            else -> null
        }

    fun formatEvent(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatEvent(packet as com.codaers.f1racehud.f1.game23.PacketEventData)
            2024 -> F1Formatters24.formatEvent(packet as com.codaers.f1racehud.f1.game24.PacketEventData)
            2025 -> F1Formatters25.formatEvent(packet as com.codaers.f1racehud.f1.game25.PacketEventData)
            else -> null
        }

    fun formatParticipants(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatParticipants(packet as com.codaers.f1racehud.f1.game23.PacketParticipantsData)
            2024 -> F1Formatters24.formatParticipants(packet as com.codaers.f1racehud.f1.game24.PacketParticipantsData)
            2025 -> F1Formatters25.formatParticipants(packet as com.codaers.f1racehud.f1.game25.PacketParticipantsData)
            else -> null
        }

    fun formatCarSetups(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatCarSetups(packet as com.codaers.f1racehud.f1.game23.PacketCarSetupData)
            2024 -> F1Formatters24.formatCarSetups(packet as com.codaers.f1racehud.f1.game24.PacketCarSetupData)
            2025 -> F1Formatters25.formatCarSetups(packet as com.codaers.f1racehud.f1.game25.PacketCarSetupData)
            else -> null
        }

    fun formatCarTelemetry(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatCarTelemetry(packet as com.codaers.f1racehud.f1.game23.PacketCarTelemetryData)
            2024 -> F1Formatters24.formatCarTelemetry(packet as com.codaers.f1racehud.f1.game24.PacketCarTelemetryData)
            2025 -> F1Formatters25.formatCarTelemetry(packet as com.codaers.f1racehud.f1.game25.PacketCarTelemetryData)
            else -> null
        }

    fun formatCarStatus(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatCarStatus(packet as com.codaers.f1racehud.f1.game23.PacketCarStatusData)
            2024 -> F1Formatters24.formatCarStatus(packet as com.codaers.f1racehud.f1.game24.PacketCarStatusData)
            2025 -> F1Formatters25.formatCarStatus(packet as com.codaers.f1racehud.f1.game25.PacketCarStatusData)
            else -> null
        }

    fun formatFinalClassification(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatFinalClassification(packet as com.codaers.f1racehud.f1.game23.PacketFinalClassificationData)
            2024 -> F1Formatters24.formatFinalClassification(packet as com.codaers.f1racehud.f1.game24.PacketFinalClassificationData)
            2025 -> F1Formatters25.formatFinalClassification(packet as com.codaers.f1racehud.f1.game25.PacketFinalClassificationData)
            else -> null
        }

    fun formatLobbyInfo(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatLobbyInfo(packet as com.codaers.f1racehud.f1.game23.PacketLobbyInfoData)
            2024 -> F1Formatters24.formatLobbyInfo(packet as com.codaers.f1racehud.f1.game24.PacketLobbyInfoData)
            2025 -> F1Formatters25.formatLobbyInfo(packet as com.codaers.f1racehud.f1.game25.PacketLobbyInfoData)
            else -> null
        }

    fun formatCarDamage(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatCarDamage(packet as com.codaers.f1racehud.f1.game23.PacketCarDamageData)
            2024 -> F1Formatters24.formatCarDamage(packet as com.codaers.f1racehud.f1.game24.PacketCarDamageData)
            2025 -> F1Formatters25.formatCarDamage(packet as com.codaers.f1racehud.f1.game25.PacketCarDamageData)
            else -> null
        }

    fun formatSessionHistory(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatSessionHistory(packet as com.codaers.f1racehud.f1.game23.PacketSessionHistoryData)
            2024 -> F1Formatters24.formatSessionHistory(packet as com.codaers.f1racehud.f1.game24.PacketSessionHistoryData)
            2025 -> F1Formatters25.formatSessionHistory(packet as com.codaers.f1racehud.f1.game25.PacketSessionHistoryData)
            else -> null
        }

    fun formatTyreSets(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatTyreSets(packet as com.codaers.f1racehud.f1.game23.PacketTyreSetsData)
            2024 -> F1Formatters24.formatTyreSets(packet as com.codaers.f1racehud.f1.game24.PacketTyreSetsData)
            2025 -> F1Formatters25.formatTyreSets(packet as com.codaers.f1racehud.f1.game25.PacketTyreSetsData)
            else -> null
        }

    fun formatMotionEx(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> F1Formatters23.formatMotionEx(packet as com.codaers.f1racehud.f1.game23.PacketMotionExData)
            2024 -> F1Formatters24.formatMotionEx(packet as com.codaers.f1racehud.f1.game24.PacketMotionExData)
            2025 -> F1Formatters25.formatMotionEx(packet as com.codaers.f1racehud.f1.game25.PacketMotionExData)
            else -> null
        }

    fun formatTimeTrial(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> null   // F1 23 does NOT have Time Trial packet
            2024 -> F1Formatters24.formatTimeTrial(packet as com.codaers.f1racehud.f1.game24.PacketTimeTrialData)
            2025 -> F1Formatters25.formatTimeTrial(packet as com.codaers.f1racehud.f1.game25.PacketTimeTrialData)
            else -> null
        }

    fun formatLapPositions(version: Int, packet: Any?): String? =
        when (version) {
            2023 -> null   // F1 23 does NOT have Lap Positions packet
            2024 -> null   // F1 24 does NOT have Lap Positions packet
            2025 -> F1Formatters25.formatLapPositions(packet as com.codaers.f1racehud.f1.game25.PacketLapPositionsData)
            else -> null
        }


}