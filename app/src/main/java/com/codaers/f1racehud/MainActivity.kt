package com.codaers.f1racehud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background


import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

import androidx.compose.ui.platform.LocalContext

import kotlinx.coroutines.flow.collectLatest

// For menu icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import android.util.Log

import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider


import android.content.Intent

class MainActivity : ComponentActivity() {
    private val telemetryViewModel: TelemetryViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set default active IDs for Motion screen
        GlobalState.activePacketIDs.value = PacketGroups.F1telemetryScreenPackets // example: Car Telemetry packetId = 6

        setContent {
            MainApp(telemetryViewModel)
        }
    }
}


@Composable
fun MainApp(viewModel: TelemetryViewModel) {
    var menuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    @OptIn(ExperimentalMaterial3Api::class)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("F1 RAW Telemetry", color = Color.White) },
                navigationIcon = {
                    Box {
                        IconButton(onClick = { menuExpanded = true }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White // force white icon
                            )
                        }
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false },
                            modifier = Modifier.width(180.dp),
                            // Shift popup down relative to the IconButton
                            offset = DpOffset(x = 0.dp, y = 26.dp) // adjust if needed (e.g., 64.dp)
                        ) {
                            DropdownMenuItem(
                                text = { Text("Home", color = Color.Black) },
                                onClick = {
                                    menuExpanded = false
                                    val intent = Intent(context, HomeActivity::class.java)
                                    context.startActivity(intent)
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Racing HUD", color = Color.Black) },
                                onClick = {
                                    menuExpanded = false
                                    val intent = Intent(context, GraphicsActivity::class.java).apply {
                                        putExtra("startDestination", "graphics") // or "graphics"
                                    }
                                    context.startActivity(intent)
                                }

                            )

                            DropdownMenuItem(
                                text = { Text("F1 Default", color = Color.Black) },
                                onClick = {
                                    menuExpanded = false
                                    val intent = Intent(context, GraphicsActivity::class.java).apply {
                                        putExtra("startDestination", "f1default") // or "graphics"
                                    }
                                    context.startActivity(intent)
                                }

                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black, // black background
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            DashboardScreen(viewModel)
        }
    }
}



@Composable
fun DashboardScreen(viewModel: TelemetryViewModel) {
    val segmentTexts = remember {
        mutableStateMapOf<Int, String>().apply {
            (0..13).forEach { put(it, "No data yet...") }
        }
    }

    val globalDefault = GlobalState.activePacketIDs.value.firstOrNull() ?: 0
    var selectedSegment by remember { mutableIntStateOf(globalDefault) }


    val version = GlobalState.gameVersion.collectAsState().value

    val segmentNames = buildList {
        addAll(
            listOf(
                "Motion", "Session", "Lap", "Event", "Participants", "Car Setups",
                "Car Telemetry", "Car Status", "Final Classification", "Lobby Info",
                "Car Damage", "Session History", "Tyre Sets", "MotionEx"
            )
        )

        // Only add Time Trial for 2024+
        if ((version ?: 0) >= 2024) {
            add("Time Trial")
        }

        // Only add Lap Positions for 2025+
        if ((version ?: 0) >= 2025) {
            add("Lap Positions")
        }

    }

    // Collect latest packet from ViewModel
    val telemetryState by viewModel.telemetryState.collectAsState(initial = null)

    // Update segmentTexts whenever telemetryState changes
    LaunchedEffect(selectedSegment) {
        val version = GlobalState.gameVersion.value ?: return@LaunchedEffect

        viewModel.telemetryState.collectLatest { packet: Any? ->

            // ✅ Safely extract header without BasePacket and without unused variable warnings
            val header = runCatching {
                packet?.javaClass?.getMethod("getHeader")?.invoke(packet) as? PacketHeader
            }.getOrNull()

            // ✅ Ignore packets without header
            if (header == null) return@collectLatest

            // ✅ Ignore packets that don't match the selected segment
            if (header.packetId.toInt() != selectedSegment) return@collectLatest

            val text = when (selectedSegment) {
                0 -> F1FormatterWrapper.formatMotion(version, packet)
                1 -> F1FormatterWrapper.formatSession(version, packet)
                2 -> F1FormatterWrapper.formatLapData(version, packet)
                3 -> F1FormatterWrapper.formatEvent(version, packet)
                4 -> F1FormatterWrapper.formatParticipants(version, packet)
                5 -> F1FormatterWrapper.formatCarSetups(version, packet)
                6 -> F1FormatterWrapper.formatCarTelemetry(version, packet)
                7 -> F1FormatterWrapper.formatCarStatus(version, packet)
                8 -> F1FormatterWrapper.formatFinalClassification(version, packet)
                9 -> F1FormatterWrapper.formatLobbyInfo(version, packet)
                10 -> F1FormatterWrapper.formatCarDamage(version, packet)
                11 -> F1FormatterWrapper.formatSessionHistory(version, packet)
                12 -> F1FormatterWrapper.formatTyreSets(version, packet)
                13 -> F1FormatterWrapper.formatMotionEx(version, packet)

                // Time Trial for 2024 and 2025
                14 -> if (version >= 2024) {
                    F1FormatterWrapper.formatTimeTrial(version, packet)
                } else null

                // Lap Positions for 2025+
                15 -> if (version >= 2025) {
                    F1FormatterWrapper.formatLapPositions(version, packet)
                } else null

                else -> null
            }

            text?.let { segmentTexts[selectedSegment] = it }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Circle indicator with click to show instructions
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            if (telemetryState != null) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.error,
                            shape = CircleShape
                        )
                )
            }

            // Top row of buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                segmentNames.forEachIndexed { id, name ->
                    Button(
                        onClick = {
                            selectedSegment = id
                            // 🔑 Update global activePacketIDs when button is clicked
                            GlobalState.activePacketIDs.value = setOf(id)
                            // Log the current active set
                            Log.d("DashboardScreen", "Active packet IDs: ${GlobalState.activePacketIDs.value}")
                        }
                    ) {
                        Text(name, color = Color.White) // force white text for readability
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            SegmentText("${segmentNames[selectedSegment]} Data\n\n${segmentTexts[selectedSegment]}")
        }
    }

}

@Composable
fun SegmentText(text: String) {
    val vScroll = rememberScrollState()
    val hScroll = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(vScroll)
            .horizontalScroll(hScroll)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White // force white text
        )
    }
}



