package com.codaers.f1racehud

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class F1RaceHUDApp : Application() {

    // Generic state flow of parsed packets
    private val _telemetryState = MutableStateFlow<Any?>(null)
    val telemetryState: StateFlow<Any?> = _telemetryState

    private val listener = UdpListener()

    override fun onCreate() {
        super.onCreate()

        // Start UDP listener once for the whole app
        CoroutineScope(Dispatchers.IO).launch {
            listener.start(
                onPacket = { packet ->
                    if (packet != null) {
                        _telemetryState.value = packet
                    }
                }
            )
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        listener.stop()
    }
}