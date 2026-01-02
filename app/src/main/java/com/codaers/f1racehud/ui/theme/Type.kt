@file:Suppress("unused", "SpellCheckingInspection")

package com.codaers.f1racehud.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.codaers.f1racehud.R

// Standalone Orbitron font family (NOT applied globally)
val Orbitron = FontFamily(
    Font(R.font.orbitron_regular, FontWeight.W400),
    Font(R.font.orbitron_medium, FontWeight.W500),
    Font(R.font.orbitron_bold, FontWeight.W700),
    Font(R.font.orbitron_black, FontWeight.W900)
)

val OrbitronMono = FontFamily(
    Font(R.font.orbitron_mono, FontWeight.W400)
)


// Default Material typography (unchanged)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)