@file:Suppress("VariableNeverRead", "AssignedValueIsNeverRead")

package com.codaers.f1racehud.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.codaers.f1racehud.TelemetryViewModel
import com.codaers.f1racehud.f1car.F1DIFFUSER
import com.codaers.f1racehud.f1car.F1FLTire
import com.codaers.f1racehud.f1car.F1FLWing
import com.codaers.f1racehud.f1car.F1FRTire
import com.codaers.f1racehud.f1car.F1FRWing
import com.codaers.f1racehud.f1car.F1Floor
import com.codaers.f1racehud.f1car.F1Front
import com.codaers.f1racehud.f1car.F1RLTire
import com.codaers.f1racehud.f1car.F1RRTire
import com.codaers.f1racehud.f1car.F1RWing
import com.codaers.f1racehud.f1car.F1Sidepods
import com.codaers.f1racehud.f1car.F1TopBody
import com.codaers.f1racehud.f1car.F1test
import kotlinx.coroutines.flow.collectLatest

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.ui.graphics.graphicsLayer

import com.codaers.f1racehud.ui.theme.Orbitron
import com.codaers.f1racehud.ui.theme.OrbitronMono

@Composable
fun GraphicsScreen(viewModel: TelemetryViewModel) {

    var lapTimeMs by remember { mutableIntStateOf(0) }
    var deltaCarInFrontMS by remember { mutableStateOf<Float?>(null) }
    var playerPosition by remember { mutableIntStateOf(0) }
    var currentLap by remember { mutableIntStateOf(1) } // from packet (ID 2)
    var penalties by remember { mutableIntStateOf(0) }
    var totalWarnings by remember { mutableIntStateOf(0) }
    var cornerCuttingWarnings by remember { mutableIntStateOf(0) }
    var numUnservedDriveThroughPens by remember { mutableIntStateOf(0) }
    var numUnservedStopGoPens by remember { mutableIntStateOf(0) }

    var totalLaps by remember { mutableIntStateOf(0) }  // from packet (ID 1)

    var gear by remember { mutableIntStateOf(0) }
    var drs by remember { mutableIntStateOf(0) }         // from status packet (ID 6)
    var drsState by remember { mutableIntStateOf(0) }    // 0=off, 1=available, 2=active
    var drsAllowed by remember { mutableIntStateOf(0) }  // from status packet (ID 7)
    var drsComing by remember { mutableIntStateOf(0) }   // from status packet (ID 7)


    var ersPercent by remember { mutableIntStateOf(0) }        // 0–100%
    var ersMode by remember { mutableIntStateOf(0) }           // 0–3

    var fuelRemainingLaps by remember { mutableFloatStateOf(0f) }

    var flWear by remember { mutableIntStateOf(0) }
    var flDamage by remember { mutableIntStateOf(0) }
    var frWear by remember { mutableIntStateOf(0) }
    var frDamage by remember { mutableIntStateOf(0) }
    var rlWear by remember { mutableIntStateOf(0) }
    var rlDamage by remember { mutableIntStateOf(0) }
    var rrWear by remember { mutableIntStateOf(0) }
    var rrDamage by remember { mutableIntStateOf(0) }

    var flWing by remember { mutableIntStateOf(0) }
    var frWing by remember { mutableIntStateOf(0) }
    var rearWing by remember { mutableIntStateOf(0) }

    var floorDamage by remember { mutableIntStateOf(0) }
    var diffuserDamage by remember { mutableIntStateOf(0) }
    var sidepodDamage by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.telemetryState.collectLatest { packet ->

            applyGraphicsPacket(
                packet,

                { totalLaps = it },
                { lapTimeMs = it },
                { deltaCarInFrontMS = it },
                { playerPosition = it },
                { currentLap = it },

                { penalties = it },
                { totalWarnings = it },
                { cornerCuttingWarnings = it },
                { numUnservedDriveThroughPens = it },
                { numUnservedStopGoPens = it },

                { gear = it },
                { drs = it },
                { drsAllowed = it },
                { drsComing = it },
                { ersPercent = it },
                { ersMode = it },
                { fuelRemainingLaps = it },

                { flWear = it },
                { frWear = it },
                { rlWear = it },
                { rrWear = it },

                { flDamage = it },
                { frDamage = it },
                { rlDamage = it },
                { rrDamage = it },

                { flWing = it },
                { frWing = it },
                { rearWing = it },

                { floorDamage = it },
                { diffuserDamage = it },
                { sidepodDamage = it },
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 2.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        // ───────────────────────────────────────────────
        // LEFT SIDE — CAR GRAPHIC + TYRES + WINGS
        // ───────────────────────────────────────────────
        Box(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight()
                .padding(start = 10.dp, top = 20.dp, bottom = 20.dp),
            contentAlignment = Alignment.Center
        ) {

            // Base car
            Image(
                painter = rememberVectorPainter(F1test),
                contentDescription = "F1 Car",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit
            )

            Image(
                painter = rememberVectorPainter(F1Front),
                contentDescription = "F1 Front Floor",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit,
                //colorFilter = ColorFilter.tint(damageColor(sidepodDamage))
                colorFilter = ColorFilter.tint(Color(0xFF2B2B2B))
            )

            Image(
                painter = rememberVectorPainter(F1Floor),
                contentDescription = "F1 Floor",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(damageColor(floorDamage))
                //colorFilter = ColorFilter.tint(Color(0x332B2B2B))
            )

            Image(
                painter = rememberVectorPainter(F1Sidepods),
                contentDescription = "F1 Sidepods",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(damageColor(sidepodDamage))
                //colorFilter = ColorFilter.tint(Color(0x332B2B2B))
            )

            Image(
                painter = rememberVectorPainter(F1TopBody),
                contentDescription = "F1 Top Body",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color(0xFF4A4F55))
            )

            Image(
                painter = rememberVectorPainter(F1DIFFUSER),
                contentDescription = "F1 Diffuser",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(damageColor(diffuserDamage))
                //colorFilter = ColorFilter.tint(Color(0xFFFF6C00))
            )

            // Front Left Tire
            val flValue = tyreValue(flWear, flDamage)
            val flColor = tyreColor(flWear, flDamage)

            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = rememberVectorPainter(F1FLTire),
                    contentDescription = "Front Left Tire",
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(flColor)
                )
                Text(
                    text = "${flValue}%",
                    style = TextStyle(color = Color.White, fontSize = 28.sp),
                    modifier = Modifier.offset(x = (-110).dp, y = (-115).dp)
                )
            }

            // Front Right Tire
            val frValue = tyreValue(frWear, frDamage)
            val frColor = tyreColor(frWear, frDamage)

            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = rememberVectorPainter(F1FRTire),
                    contentDescription = "Front Right Tire",
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(frColor)
                )
                Text(
                    text = "${frValue}%",
                    style = TextStyle(color = Color.White, fontSize = 28.sp),
                    modifier = Modifier.offset(x = 130.dp, y = (-115).dp)
                )
            }

            // Rear Left Tire
            val rlValue = tyreValue(rlWear, rlDamage)
            val rlColor = tyreColor(rlWear, rlDamage)

            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = rememberVectorPainter(F1RLTire),
                    contentDescription = "Rear Left Tire",
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(rlColor)
                )
                Text(
                    text = "${rlValue}%",
                    style = TextStyle(color = Color.White, fontSize = 28.sp),
                    modifier = Modifier.offset(x = (-110).dp, y = 140.dp)
                )
            }

            // Rear Right Tire
            val rrValue = tyreValue(rrWear, rrDamage)
            val rrColor = tyreColor(rrWear, rrDamage)

            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = rememberVectorPainter(F1RRTire),
                    contentDescription = "Rear Right Tire",
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(rrColor)
                )
                Text(
                    text = "${rrValue}%",
                    style = TextStyle(color = Color.White, fontSize = 28.sp),
                    modifier = Modifier.offset(x = 130.dp, y = 140.dp)
                )
            }

            // Wings
            Image(
                painter = rememberVectorPainter(F1RWing),
                contentDescription = "Rear Wing",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(damageWingsColor(rearWing))
            )
            Image(
                painter = rememberVectorPainter(F1FRWing),
                contentDescription = "Front Right Wing",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(damageWingsColor(frWing))
            )
            Image(
                painter = rememberVectorPainter(F1FLWing),
                contentDescription = "Front Left Wing",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(damageWingsColor(flWing))
            )
        }

        // ───────────────────────────────────────────────
        // CENTER — LAP TIME + GEAR
        // ───────────────────────────────────────────────
        Column(
            modifier = Modifier
                .weight(1.4f)
                .fillMaxHeight()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = formatLap(lapTimeMs),
                //text = "01:21:567",
                style = MaterialTheme.typography.headlineMedium.copy(fontFamily = OrbitronMono, fontSize = 48.sp),
                color = Color.White,
                modifier = Modifier.padding(bottom = 0.dp)
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // BIG GEAR NUMBER
                AnimatedGear(gear)

                // RIGHT‑SIDE VERTICAL BADGES
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                    horizontalAlignment = Alignment.End
                ) {
                    // 🕒 Time penalties (seconds)
                    StatusBadge(
                        text = "🕒 +${penalties}s",
                        active = penalties > 0
                        //text = "🕒 +12s",
                        //active = true
                    )

                    // ⚠️ Total warnings
                    //StatusBadge(
                    //    text = "⚠️ ${totalWarnings % 3}",
                    //    active = totalWarnings != 0 && totalWarnings % 3 != 0
                    //)

                    // ⚠️ Corner cutting warnings
                    StatusBadge(
                        text = "⚠️ ${cornerCuttingWarnings % 3}",
                        //active = true
                        active = cornerCuttingWarnings != 0 && cornerCuttingWarnings % 3 != 0
                    )

                    // ➡️ Unserved drive‑through penalties
                    StatusBadge(
                        text = "➡️ $numUnservedDriveThroughPens",
                        active = numUnservedDriveThroughPens > 0
                    )

                    // 🛑 Unserved stop‑go penalties
                    StatusBadge(
                        text = "🛑 $numUnservedStopGoPens",
                        active = numUnservedStopGoPens > 0
                    )
                    Spacer(modifier = Modifier.height(46.dp))
                }
            }

            // ───────────────────────────────
            // ERS SECTION (text above bar)
            // ───────────────────────────────

            val fillColor = Color(0xFFFFD700)   // Yellow

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$ersPercent%  ${ersModeName(ersMode)}",
                    //text = "100%  OVERTAKE",
                    color = Color.Yellow    ,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(36.dp)
                        .border(
                            width = 2.dp,
                            color = fillColor,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .background(Color(0xFF222222), RoundedCornerShape(6.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(ersPercent / 100f)
                            .background(fillColor, RoundedCornerShape(6.dp))
                    )
                }
            }
        }

        // RIGHT SIDE HUD (Gap Ahead, Position, Lap, DRS, Fuel)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(top = 30.dp, end = 5.dp),
            horizontalAlignment = Alignment.End
        ) {

            // ───────────────────────────────
            // GAP TO CAR AHEAD
            // ───────────────────────────────
            val (gapText, gapFontSize, gapFont) = when (deltaCarInFrontMS) {
                null -> Triple("No car ahead", 30.sp, Orbitron)
                else -> Triple("+${"%.3f".format(deltaCarInFrontMS)} s", 40.sp, OrbitronMono)
            }

            Text(
                text = gapText,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = gapFont,
                    fontSize = gapFontSize,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )


            // POSITION (added below GAP)
            Text(
                text = "P: $playerPosition",
                color = Color.Yellow,
                style = MaterialTheme.typography.headlineSmall.copy(fontFamily = Orbitron, fontSize = 42.sp),
                modifier = Modifier.padding(bottom = 14.dp, end = 42.dp)
            )

            // LAP COUNTER (added at bottom)
            Text(
                text = "Lap $currentLap/$totalLaps",
                //text = "Lap 22/28",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall.copy(fontFamily = Orbitron, fontSize = 38.sp),
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // ───────────────────────────────
            // DRS SECTION
            // ───────────────────────────────
            drsState = when {
                drs == 1 -> 2                         // ACTIVE
                drsAllowed == 1 || drsComing > 0 -> 1 // AVAILABLE
                else -> 0                             // OFF
            }

            val (drsText, drsColor) = when (drsState) {
                2 -> "DRS ACTIVE" to Color.Green
                1 -> "DRS READY" to Color.Yellow
                else -> "DRS OFF" to Color.Gray
            }

            Text(
                text = drsText,
                color = drsColor,
                style = MaterialTheme.typography.headlineSmall.copy(fontFamily = Orbitron, fontSize = 30.sp)
            )

            Text(
                text = if (drsComing > 0) "DRS in ${drsComing}m" else " ",
                color = Color.Yellow.copy(alpha = if (drsComing > 0) 1f else 0f),
                style = MaterialTheme.typography.bodyLarge.copy(fontFamily = Orbitron, fontSize = 30.sp)
            )

            Spacer(Modifier.height(24.dp))

            // ───────────────────────────────
            // FUEL SECTION
            // ───────────────────────────────
            Text(
                text = "Fuel",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall.copy(fontFamily = Orbitron, fontSize = 30.sp),
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "${"%.2f".format(fuelRemainingLaps)} laps",
                color = if (fuelRemainingLaps < 0f) Color.Red else Color.White,
                style = MaterialTheme.typography.bodyLarge.copy(fontFamily = Orbitron, fontSize = 30.sp),
            )
        }

    }
}

@Composable
fun StatusBadge(
    text: String,
    active: Boolean = true
) {
    val bgColor = if (active) Color.DarkGray else Color.DarkGray.copy(alpha = 0.3f)
    val textColor = if (active) Color.White else Color.White.copy(alpha = 0.15f)

    // Create a lighter edge color based on bgColor
    val edgeColor = if (active) Color(0xFF555555) else Color.DarkGray

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = edgeColor,
                shape = RoundedCornerShape(6.dp)
            )
            .background(bgColor, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 24.sp
        )
    }
}

@Composable
fun AnimatedGear(gear: Int) {

    val (gearText, gearColor) = when (gear) {
        -1 -> "R" to Color(0xFFDA0010)
        0  -> "N" to Color.White
        else -> gear.toString() to Color.Green
    }

    // Animatable values
    val scale = remember { Animatable(1f) }
    val alpha = remember { Animatable(1f) }

    // Trigger animation when gear changes
    LaunchedEffect(gearText) {
        // Pop effect
        scale.snapTo(1.2f)
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(180, easing = LinearOutSlowInEasing)
        )

        // Fade effect
        alpha.snapTo(0.3f)
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(180, easing = LinearOutSlowInEasing)
        )
    }

    // Render animated gear
    Text(
        text = gearText,
        style = MaterialTheme.typography.headlineMedium.copy(
            fontSize = 188.sp,
            fontFamily = Orbitron,
            fontWeight = FontWeight.Bold
        ),
        color = gearColor.copy(alpha = alpha.value),
        modifier = Modifier.graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
        }
    )
}
