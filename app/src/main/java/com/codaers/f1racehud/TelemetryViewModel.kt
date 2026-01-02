package com.codaers.f1racehud

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.StateFlow

class TelemetryViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as F1RaceHUDApp

    // Expose the shared telemetry state as generic Any?
    val telemetryState: StateFlow<Any?> = app.telemetryState
}