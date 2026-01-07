package com.codaers.f1racehud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle


import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.style.TextAlign

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

// New imports for modern IP detection
import java.net.Inet4Address
import java.net.NetworkInterface

// For menu icon
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.Image

import android.content.Intent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.compose.foundation.background

import com.codaers.f1racehud.ui.theme.Orbitron
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.googlefonts.isAvailableOnDevice
import android.util.Log
import com.codaers.f1racehud.ui.theme.Iceberg
import androidx.compose.ui.platform.LocalConfiguration


import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.Brush

import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.geometry.Offset


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val pacifico = GoogleFont("Bungee Shade")
val pacificoFamily = FontFamily(
    Font(
        googleFont = pacifico,
        fontProvider = provider
    )
)

private const val TAG = "HomeScreen"
class HomeActivity : ComponentActivity() {
    /*
    private val telemetryViewModel: TelemetryViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContent {
            HomeScreen(
                onF1DefaultClick = {
                    startActivity(
                        Intent(this, GraphicsActivity::class.java).apply {
                            putExtra("startDestination", "f1default")
                        }
                    )
                },
                onTelemetryClick = { startActivity(Intent(this, MainActivity::class.java)) },
                onRaceHudClick = {
                    startActivity(
                    Intent(this, GraphicsActivity::class.java).apply {
                        putExtra("startDestination", "graphics")
                    }
                ) },
                onExitClick = {
                    finishAffinity()
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()

        // Re-apply fullscreen after dialogs or focus changes
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

data class MenuEntry(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun HomeScreen(
    onF1DefaultClick: () -> Unit,
    onTelemetryClick: () -> Unit,
    onRaceHudClick: () -> Unit,
    onExitClick: () -> Unit
) {

    val backgroundPainter = painterResource(id = R.drawable.home_background)

    var showInstructions by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }
    var selectedMenu by remember { mutableStateOf<String?>(null) }

    val deviceIp = remember { getLocalIp() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (provider.isAvailableOnDevice(context)) {
            Log.d(TAG, "Success Font Load!")
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundPainter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontFamily = Orbitron,
                    fontWeight = FontWeight.W700
                ),
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            val menuItems = listOf(
                MenuEntry( "Race HUD", Icons.Filled.Flag ) {
                    selectedMenu = "Race HUD"
                    onRaceHudClick()
                },
                MenuEntry( "Telemetry", Icons.AutoMirrored.Filled.ShowChart ) {
                    selectedMenu = "Telemetry"
                    onTelemetryClick()
                    GlobalState.activePacketIDs.value = PacketGroups.F1telemetryScreenPackets
                },
                MenuEntry( "F1 Default", Icons.Filled.Speed ) {
                    selectedMenu = "F1 Default"
                    onF1DefaultClick()
                },
                MenuEntry( "About", Icons.Filled.DirectionsCar ) {
                    selectedMenu = "About"
                    showAbout = true
                },
                MenuEntry( "Settings", Icons.Filled.Settings ) {
                    selectedMenu = "Settings"
                    showInstructions = true
                },
                MenuEntry( "Exit App", Icons.AutoMirrored.Filled.ExitToApp ) {
                    selectedMenu = "Exit App"
                    onExitClick()
                }
            )

            val config = LocalConfiguration.current
            val isLandscape =
                config.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

            if (!isLandscape) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(menuItems.size) { index ->
                        val item = menuItems[index]
                        MenuButton(
                            item.label,
                            item.icon,
                            onClick = item.onClick,
                            highlight = selectedMenu == item.label
                        )
                    }
                }
            }
            else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(Modifier.height(22.dp))

                    // FIRST ROW (4 items)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        menuItems.take(4).forEach { item ->
                            Box(Modifier.size(140.dp)) {
                                MenuButton(
                                    item.label,
                                    item.icon,
                                    onClick = item.onClick,
                                    highlight = selectedMenu == item.label
                                )
                            }
                        }
                    }

                    // SECOND ROW (2 centered items)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        menuItems.drop(4).forEach { item ->
                            Box(
                                Modifier
                                    .padding(horizontal = 12.dp)
                                    .size(140.dp)
                            ) {
                                MenuButton(
                                    item.label,
                                    item.icon,
                                    onClick = item.onClick,
                                    highlight = selectedMenu == item.label
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showInstructions) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xAA000000)) // dim background
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                        .widthIn(max = 420.dp) // max Width of Dialog
                        .background(Color(0xFF1A1A1A), RoundedCornerShape(12.dp))
                        .padding(24.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "F1 Telemetry Setup",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("1. Open F1 → Settings → Telemetry.", color = Color.White)
                            Text("2. Set UDP Mode = Enabled.", color = Color.White)

                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // First line: sentence with label only
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "3. Enter your device’s IP address: ",
                                        color = Color.White
                                    )
                                }

                                // Second line: IP centered
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = deviceIp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }


                            Text("4. Set Port = 20777.", color = Color.White)
                            Text("5. Choose Frequency = 60Hz or 120Hz.", color = Color.White)
                            Text("6. Save and return to the game.", color = Color.White)
                        }

                        Button(
                            onClick = {
                                showInstructions = false
                                selectedMenu = null
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("OK")
                        }
                    }
                }
            }
        }
        val scrollState = rememberScrollState()

        if (showAbout) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xAA000000)) // dim background
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                        .widthIn(max = 420.dp) // max Width of Dialog
                        .background(Color(0xFF1A1A1A), RoundedCornerShape(12.dp))
                        .padding(24.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.verticalScroll(scrollState)
                    ) {

                        // ICON LEFT + TEXTS RIGHT
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                                contentDescription = "App Icon",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(96.dp)
                            )

                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    color = Color.White,
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontFamily = Orbitron
                                    ),
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Version ${BuildConfig.VERSION_NAME}",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        // Generic About Text
                        Text(
                            text = "F1 Race HUD provides live race data, " +
                                    "performance insights, and real-time overlays.\n\n" +
                                    "Developed with passion for motorsport.",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )

                        HorizontalDivider(color = Color.Gray)

                        // Copyright / Footer
                        Text(
                            text = buildAnnotatedString {
                                append("© 2025 ")

                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = Iceberg
                                    )
                                ) {
                                    append("CODÆRS")
                                }

                                append(". All rights reserved.")
                            },
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )

                        // OK Button
                        Button(
                            onClick = {
                                showAbout = false
                                selectedMenu = null
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("OK")
                        }
                    }
                }
            }
        }

    }
}
fun rotatedBrush(angleDegrees: Float): Brush {
    val rad = Math.toRadians(angleDegrees.toDouble())
    val x = cos(rad).toFloat()
    val y = sin(rad).toFloat()

    return Brush.linearGradient(
        colors = listOf(
            Color(0xFF2F2F2F),
            Color(0xFF383838),
            Color(0xFF2A2A2A),
            Color(0xFF3E3E3E),
            Color(0xFF323232)
        ),
        start = Offset.Zero,
        end = Offset(x * 1000f, y * 1000f),
        tileMode = TileMode.Mirror
    )
}

@Composable
fun MenuButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    highlight: Boolean = false
) {
    val borderColor = if (highlight) Color(0xFFFF6F00) else Color(0xFF444444)
    val contentColor = Color(0xFFFF6F00)
    val backgroundColor = Color(0xFF000000).copy(alpha = 0.5f)

    // Procedural noise brush (no Offset needed)
    val noiseBrush = remember {
        Brush.linearGradient(
            colors = listOf(
                Color(0xFF2F2F2F),
                Color(0xFF383838),
                Color(0xFF2A2A2A),
                Color(0xFF3E3E3E),
                Color(0xFF323232)
            ),
            start = Offset.Zero,
            end = Offset(800f, 300f), // ← rotation direction
            tileMode = TileMode.Mirror
        )
    }



    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        border = BorderStroke(0.dp, Color.Transparent), // we draw our own border
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .drawBehind {
                val radius = 12.dp.toPx()
                val strokeWidth = 3.dp.toPx()

                if (highlight) {
                    // Clean bright border
                    drawRoundRect(
                        color = borderColor,
                        cornerRadius = CornerRadius(radius),
                        style = Stroke(width = strokeWidth)
                    )
                } else {
                    // Textured / scattered border
                    drawRoundRect(
                        brush = noiseBrush,
                        cornerRadius = CornerRadius(radius),
                        style = Stroke(width = strokeWidth)
                    )
                }
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = contentColor,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = label,
                color = contentColor,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

fun getLocalIp(): String {
    // Iterate over all interfaces and addresses
    NetworkInterface.getNetworkInterfaces().toList().forEach { intf ->
        intf.inetAddresses.toList().forEach { addr ->
            if (!addr.isLoopbackAddress && addr is Inet4Address) {
                return addr.hostAddress ?: "Unknown"
            }
        }
    }
    return "Unknown"
}


