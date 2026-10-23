package com.codaers.f1racehud.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codaers.f1racehud.TelemetryViewModel
import com.codaers.f1racehud.dashboard.rpm
import com.codaers.f1racehud.ui.theme.F1SimDashboardTheme

@Composable
fun F1DefaultScreen(viewModel: TelemetryViewModel) {
    val telemetryState = viewModel.telemetryState.collectAsState(initial = null).value

    var speed by remember { mutableIntStateOf(0) }
    var rpmValue by remember { mutableIntStateOf(0) }
    var gear by remember { mutableIntStateOf(0) }

    LaunchedEffect(telemetryState) {
        when (telemetryState) {
            is com.codaers.f1racehud.f1.game23.PacketCarTelemetryData -> {
                val carData = telemetryState.carTelemetryData[telemetryState.header.playerCarIndex.toInt()]
                speed = carData.speed.toInt()
                rpmValue = carData.engineRPM.toInt()
                gear = carData.gear.toInt()
            }

            is com.codaers.f1racehud.f1.game24.PacketCarTelemetryData -> {
                val carData = telemetryState.carTelemetryData[telemetryState.header.playerCarIndex.toInt()]
                speed = carData.speed.toInt()
                rpmValue = carData.engineRPM.toInt()
                gear = carData.gear.toInt()
            }

            is com.codaers.f1racehud.f1.game25.PacketCarTelemetryData -> {
                val carData = telemetryState.carTelemetryData[telemetryState.header.playerCarIndex.toInt()]
                speed = carData.speed.toInt()
                rpmValue = carData.engineRPM.toInt()
                gear = carData.gear.toInt()
            }

            is com.codaers.f1racehud.f1.game26.PacketCarTelemetryData -> {
                val carData = telemetryState.carTelemetryData[telemetryState.header.playerCarIndex.toInt()]
                speed = carData.speed.toInt()
                rpmValue = carData.engineRPM.toInt()
                gear = carData.gear.toInt()
            }
        }
    }

    val rpmSegment = (((rpmValue - 3000).coerceIn(0, 9000)) / 9000f * 15)
        .toInt()
        .coerceIn(0, 15)

    F1DefaultScreenContent(
        speed = speed,
        rpm = rpmValue,
        gear = gear,
        rpmSegment = rpmSegment
    )
}

@Composable
fun F1DefaultScreenContent(
    speed: Int,
    rpm: Int,
    gear: Int,
    rpmSegment: Int
) {
    // Added Surface with Color.Black to ensure the background matches the app's black container
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 0.dp, end = 32.dp, top = 32.dp, bottom = 32.dp)
                .offset(y = (-10).dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // LEFT — RPM (fixed size)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
            ) {
                Image(
                    imageVector = rpm(rpmSegment),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = "$rpm RPM",
                    style = TextStyle(color = Color.White, fontSize = 38.sp),
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 10.dp)
                )
            }

            // CENTER — Speed (TRUE CENTER)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$speed",
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 152.sp),
                    color = Color.Yellow
                )
            }

            // RIGHT — Gear (fixed width to balance left)
            Box(
                modifier = Modifier.width(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$gear",
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 152.sp),
                    color = Color(0xFFFF8C00)
                )
            }
        }
    }
}

@Preview(
    name = "Default",
    showBackground = true,
    device = "spec:width=800dp,height=400dp"
)
@Composable
fun F1DefaultScreenPreview() {
    F1SimDashboardTheme {
        // Wrapped in Surface to ensure a stable preview environment and correct background
        Surface(color = Color.Black) {
            F1DefaultScreenContent(
                speed = 300,
                rpm = 8000,
                gear = 5,
                rpmSegment = 10
            )
        }
    }
}