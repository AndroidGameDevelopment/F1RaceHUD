package com.codaers.f1racehud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import android.app.Activity
import android.view.WindowManager
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.platform.LocalContext

// For menu icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu

import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

import android.content.Intent

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.getValue

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput


import com.codaers.f1racehud.screens.F1DefaultScreen
import com.codaers.f1racehud.screens.GraphicsScreen


class GraphicsActivity : ComponentActivity() {

    private val telemetryViewModel: TelemetryViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Read which screen to open (default = "graphics")
        val startDestination = intent.getStringExtra("startDestination") ?: "graphics"

        // Set default active IDs for CarTelemetry screen
        GlobalState.activePacketIDs.value = PacketGroups.GraphicsScreenPackets  // Defined on top of file

        setContent {
            GraphicsApp(
                viewModel = telemetryViewModel,
                startDestination = startDestination
            )
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemBars()
        }
    }

    private fun hideSystemBars() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}


@Composable
fun GraphicsApp(
    viewModel: TelemetryViewModel,
    startDestination: String
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var showTopBar by remember { mutableStateOf(true) }
    val context = LocalContext.current

    // Auto-hide after 3 seconds
    LaunchedEffect(showTopBar) {
        if (showTopBar) {
            kotlinx.coroutines.delay(3000)
            showTopBar = false
        }
    }

    // fullscreen system bar handling
    DisposableEffect(Unit) {
        val window = (context as Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        onDispose { }
    }

    val navController = rememberNavController()

    @OptIn(ExperimentalMaterial3Api::class)
    Scaffold(
        containerColor = Color.Black,

        // Tap anywhere to show the bar again
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures {
                showTopBar = true
            }
        },

        topBar = {
            if (showTopBar) {
                TopAppBar(
                    title = { Text("F1 Graphics", color = Color.White) },
                    navigationIcon = {
                        Box {
                            IconButton(onClick = {
                                menuExpanded = true
                                showTopBar = true   // keep bar visible while menu is open
                            }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White
                                )
                            }

                            DropdownMenu(
                                expanded = menuExpanded,
                                onDismissRequest = { menuExpanded = false },
                                modifier = Modifier.width(180.dp)
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
                                    text = { Text("Telemetry", color = Color.Black) },
                                    onClick = {
                                        menuExpanded = false
                                        val intent = Intent(context, MainActivity::class.java)
                                        context.startActivity(intent)
                                        (context as? Activity)?.finish()
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text("Racing HUD", color = Color.Black) },
                                    onClick = {
                                        menuExpanded = false
                                        navController.navigate("graphics")
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text("F1 Default", color = Color.Black) },
                                    onClick = {
                                        menuExpanded = false
                                        navController.navigate("f1default")
                                    }
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("graphics") {
                LaunchedEffect(Unit) {
                    GlobalState.activePacketIDs.value = PacketGroups.GraphicsScreenPackets  // Defined on top of file
                    Log.d("Telemetry", "Active packets changed → ${GlobalState.activePacketIDs.value}")
                }
                GraphicsScreen(viewModel)
            }

            composable("f1default") {
                LaunchedEffect(Unit) {
                    GlobalState.activePacketIDs.value = PacketGroups.F1DefaultScreenPackets // Defined on top of file
                    Log.d("Telemetry", "Active packets changed → ${GlobalState.activePacketIDs.value}")
                }
                F1DefaultScreen(viewModel)
            }
        }
    }
}
