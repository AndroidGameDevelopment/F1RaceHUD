package com.codaers.f1racehud

import kotlinx.coroutines.flow.MutableStateFlow

object GlobalState {
    val activePacketIDs: MutableStateFlow<Set<Int>> = MutableStateFlow(emptySet())
    val gameVersion = MutableStateFlow<Int?>(null) // 2023, 2024, 2025
}

object PacketGroups {
    val GraphicsScreenPackets = setOf(1, 2, 6, 7, 10)
    val F1DefaultScreenPackets = setOf(6)
    val F1telemetryScreenPackets = setOf(6)
}