package com.codaers.f1racehud.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Box


private val segmentBaseColors = listOf(
    Color(0xFF2cff00), // segment 0
    Color(0xFF2cff00), // segment 1
    Color(0xFF2cff00), // segment 2
    Color(0xFF2cff00), // segment 3
    Color(0xFFa2ff00), // segment 4
    Color(0xFFffff02), // segment 5
    Color(0xFFffff02), // segment 6
    Color(0xFFffff02), // segment 7
    Color(0xFFffff02), // segment 8
    Color(0xFFffff02), // segment 9
    Color(0xFFffff02), // segment 10
    Color(0xFFffff02), // segment 11
    Color(0xFFffd700), // segment 12
    Color(0xFFff5a01), // segment 13
    Color(0xFFff0001), // segment 14
)

fun rpm(rpmValue: Int): ImageVector {

    fun segColor(index: Int): Color =
        if (rpmValue > index) {
            segmentBaseColors[index] // ON color
        } else {
            Color.DarkGray           // OFF color
        }


    return ImageVector.Builder(
        name = "RPM",
        defaultWidth = 2349.0.dp,
        defaultHeight = 2871.0.dp,
        viewportWidth = 2349.0f,
        viewportHeight = 2871.0f
    ).apply {

        // SEGMENT 0
        path(
            fill = SolidColor(segColor(0)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(596.5f, 2869.6f)
            curveToRelative(-1.6f, -0.7f, -6.7f, -4.7f, -11.2f, -8.7f)
            curveToRelative(-14.6f, -13.1f, -85.8f, -87.6f, -119.0f, -124.6f)
            curveToRelative(-33.1f, -36.9f, -46.6f, -51.4f, -52.4f, -56.5f)
            curveToRelative(-3.2f, -2.8f, -5.9f, -5.4f, -5.9f, -5.7f)
            curveToRelative(0.0f, -0.4f, 5.7f, -7.7f, 12.6f, -16.4f)
            curveToRelative(11.5f, -14.4f, 12.8f, -15.7f, 15.7f, -15.7f)
            curveToRelative(4.1f, 0.0f, 7.7f, -2.5f, 7.8f, -5.3f)
            curveToRelative(0.0f, -1.6f, 0.3f, -1.2f, 1.2f, 1.3f)
            lineToRelative(1.2f, 3.5f)
            lineToRelative(9.3f, 0.2f)
            curveToRelative(7.9f, 0.1f, 10.2f, 0.6f, 14.7f, 2.8f)
            curveToRelative(6.9f, 3.3f, 11.1f, 8.2f, 32.0f, 37.5f)
            curveToRelative(53.6f, 74.9f, 117.9f, 156.1f, 127.0f, 160.4f)
            curveToRelative(1.9f, 1.0f, 3.5f, 2.1f, 3.5f, 2.5f)
            curveToRelative(0.0f, 2.3f, -5.0f, 6.5f, -12.2f, 10.2f)
            curveToRelative(-4.7f, 2.5f, -10.5f, 6.6f, -14.1f, 10.1f)
            curveToRelative(-3.3f, 3.2f, -6.3f, 5.8f, -6.6f, 5.8f)
            curveToRelative(-0.3f, -0.1f, -1.9f, -0.7f, -3.6f, -1.4f)
            close()
        }
        // SEGMENT 1
        path(
            fill = SolidColor(segColor(1)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(388.5f, 2652.4f)
            curveToRelative(-4.3f, -4.1f, -7.2f, -7.2f, -6.3f, -6.9f)
            curveToRelative(2.4f, 1.0f, 8.6f, -1.3f, 9.8f, -3.6f)
            curveToRelative(2.3f, -4.3f, 3.0f, -13.3f, 4.0f, -51.4f)
            curveToRelative(1.3f, -51.9f, 2.3f, -59.5f, 7.2f, -58.2f)
            curveToRelative(1.0f, 0.3f, 2.1f, 0.1f, 2.4f, -0.5f)
            curveToRelative(0.7f, -1.0f, 2.0f, 0.9f, 7.8f, 11.7f)
            curveToRelative(3.1f, 5.7f, 3.3f, 6.5f, 1.3f, 4.3f)
            lineToRelative(-2.5f, -2.7f)
            lineToRelative(-0.5f, 43.2f)
            curveToRelative(-0.5f, 44.9f, -1.0f, 50.8f, -4.8f, 54.3f)
            curveToRelative(-3.0f, 2.7f, -2.2f, 3.4f, 2.4f, 1.9f)
            curveToRelative(2.7f, -0.9f, 3.5f, -1.0f, 2.3f, -0.2f)
            curveToRelative(-4.5f, 2.9f, -7.6f, 6.0f, -10.6f, 10.7f)
            curveToRelative(-1.8f, 2.8f, -3.5f, 5.0f, -3.9f, 5.0f)
            curveToRelative(-0.3f, -0.1f, -4.2f, -3.5f, -8.6f, -7.6f)
            close()
            moveTo(380.8f, 2644.8f)
            curveToRelative(-0.8f, -0.7f, -5.7f, -6.9f, -10.8f, -13.7f)
            curveToRelative(-5.1f, -6.8f, -14.0f, -17.8f, -19.7f, -24.5f)
            curveToRelative(-17.3f, -20.0f, -27.5f, -34.7f, -30.4f, -43.4f)
            curveToRelative(-0.9f, -2.8f, -0.8f, -3.5f, 0.6f, -4.6f)
            curveToRelative(2.7f, -2.0f, 3.4f, -8.6f, 3.5f, -33.3f)
            lineToRelative(0.0f, -23.3f)
            lineToRelative(16.0f, 0.0f)
            lineToRelative(16.0f, 0.0f)
            lineToRelative(0.0f, 22.3f)
            curveToRelative(0.1f, 12.2f, 0.5f, 34.8f, 1.0f, 50.2f)
            curveToRelative(0.8f, 27.2f, 2.2f, 39.7f, 4.1f, 37.8f)
            curveToRelative(2.0f, -2.0f, 3.0f, -13.9f, 3.9f, -45.8f)
            curveToRelative(1.3f, -45.7f, 3.0f, -54.0f, 12.4f, -61.3f)
            curveToRelative(2.0f, -1.5f, 4.3f, -2.2f, 7.2f, -2.2f)
            curveToRelative(3.6f, 0.0f, 5.1f, 0.6f, 8.4f, 3.6f)
            curveToRelative(4.4f, 4.0f, 11.8f, 17.8f, 12.2f, 22.9f)
            curveToRelative(0.3f, 2.5f, 0.0f, 3.0f, -1.8f, 2.7f)
            curveToRelative(-1.2f, -0.1f, -2.6f, 0.4f, -3.3f, 1.1f)
            curveToRelative(-2.1f, 2.6f, -3.1f, 16.6f, -4.1f, 57.2f)
            curveToRelative(-1.2f, 48.6f, -1.8f, 52.2f, -8.1f, 54.4f)
            curveToRelative(-4.1f, 1.4f, -5.2f, 1.4f, -7.1f, -0.1f)
            close()
            moveTo(405.0f, 2645.1f)
            curveToRelative(0.0f, -0.4f, 0.9f, -1.6f, 1.9f, -2.5f)
            curveToRelative(3.8f, -3.5f, 4.3f, -9.4f, 4.8f, -54.4f)
            lineToRelative(0.5f, -43.4f)
            lineToRelative(11.1f, 14.9f)
            curveToRelative(12.8f, 17.1f, 26.7f, 38.0f, 26.7f, 40.1f)
            curveToRelative(0.0f, 0.8f, -0.6f, 1.2f, -1.4f, 0.9f)
            curveToRelative(-2.6f, -1.0f, -3.6f, 1.7f, -3.6f, 9.6f)
            curveToRelative(0.0f, 7.3f, 0.2f, 8.0f, 2.9f, 10.8f)
            curveToRelative(1.6f, 1.6f, 3.7f, 2.9f, 4.5f, 2.9f)
            curveToRelative(0.9f, 0.0f, 1.6f, 0.5f, 1.6f, 1.0f)
            curveToRelative(0.0f, 0.6f, -1.5f, 1.0f, -3.4f, 1.0f)
            curveToRelative(-1.9f, 0.0f, -4.7f, 0.5f, -6.1f, 1.0f)
            curveToRelative(-2.3f, 0.9f, -2.6f, 1.5f, -2.1f, 3.6f)
            curveToRelative(0.7f, 2.5f, 0.5f, 2.5f, -2.6f, 1.9f)
            curveToRelative(-1.8f, -0.4f, -3.5f, -0.7f, -3.8f, -0.7f)
            curveToRelative(-4.1f, -0.1f, -6.9f, 1.0f, -13.7f, 5.6f)
            curveToRelative(-7.8f, 5.1f, -17.3f, 9.4f, -17.3f, 7.7f)
            close()
            moveTo(309.3f, 2549.2f)
            curveToRelative(-10.1f, -9.7f, -28.3f, -37.4f, -28.3f, -43.1f)
            curveToRelative(0.0f, -2.4f, 1.9f, -2.9f, 15.6f, -3.7f)
            lineToRelative(11.1f, -0.7f)
            lineToRelative(0.7f, 17.5f)
            curveToRelative(0.7f, 18.6f, 2.4f, 27.9f, 5.6f, 31.4f)
            curveToRelative(1.1f, 1.1f, 2.0f, 2.3f, 2.0f, 2.7f)
            curveToRelative(0.0f, 1.6f, -2.0f, 0.4f, -6.7f, -4.1f)
            close()
            moveTo(447.9f, 2621.1f)
            curveToRelative(-2.7f, -2.8f, -2.9f, -3.4f, -2.9f, -10.9f)
            curveToRelative(0.0f, -8.9f, 1.2f, -11.2f, 4.6f, -8.8f)
            curveToRelative(2.2f, 1.6f, 7.1f, 11.2f, 6.2f, 12.2f)
            curveToRelative(-0.4f, 0.3f, -1.6f, 0.1f, -2.7f, -0.6f)
            curveToRelative(-1.2f, -0.6f, -2.6f, -0.8f, -3.1f, -0.5f)
            curveToRelative(-1.8f, 1.1f, -1.1f, 6.8f, 1.2f, 9.2f)
            curveToRelative(3.2f, 3.4f, 0.2f, 2.8f, -3.3f, -0.6f)
            close()
            moveTo(359.0f, 2607.8f)
            curveToRelative(-1.3f, -6.0f, -2.9f, -50.5f, -3.0f, -83.5f)
            lineToRelative(0.0f, -22.3f)
            lineToRelative(-16.0f, 0.0f)
            lineToRelative(-16.0f, 0.0f)
            lineToRelative(0.0f, 23.3f)
            curveToRelative(-0.1f, 25.3f, -0.7f, 30.8f, -3.8f, 33.7f)
            curveToRelative(-2.0f, 1.9f, -2.1f, 1.9f, -5.6f, -2.0f)
            curveToRelative(-4.1f, -4.6f, -4.5f, -5.5f, -1.5f, -4.0f)
            curveToRelative(2.7f, 1.5f, 3.6f, 0.5f, 1.8f, -1.7f)
            curveToRelative(-4.6f, -5.6f, -6.8f, -18.8f, -6.9f, -40.7f)
            lineToRelative(0.0f, -8.9f)
            lineToRelative(-10.9f, 0.7f)
            curveToRelative(-6.1f, 0.3f, -12.3f, 0.9f, -14.0f, 1.2f)
            curveToRelative(-2.9f, 0.6f, -3.1f, 0.4f, -7.2f, -7.7f)
            curveToRelative(-5.4f, -10.8f, -13.2f, -22.7f, -23.0f, -34.9f)
            curveToRelative(-8.7f, -10.9f, -16.4f, -22.3f, -17.4f, -25.8f)
            curveToRelative(-0.5f, -1.9f, 0.6f, -3.6f, 6.7f, -9.8f)
            curveToRelative(10.0f, -10.4f, 15.3f, -13.3f, 6.6f, -3.7f)
            lineToRelative(-4.3f, 4.7f)
            lineToRelative(7.0f, -5.8f)
            curveToRelative(9.0f, -7.5f, 14.8f, -12.9f, 17.2f, -15.9f)
            curveToRelative(3.4f, -4.4f, 9.7f, -5.7f, 27.1f, -5.6f)
            curveToRelative(9.1f, 0.0f, 14.9f, 0.4f, 13.7f, 0.9f)
            curveToRelative(-3.1f, 1.2f, 4.5f, 2.4f, 10.3f, 1.6f)
            curveToRelative(7.2f, -1.1f, 8.9f, -0.8f, 13.0f, 1.8f)
            curveToRelative(8.4f, 5.4f, 12.8f, 12.2f, 31.6f, 49.5f)
            curveToRelative(16.2f, 31.9f, 23.6f, 45.0f, 30.2f, 53.0f)
            curveToRelative(5.9f, 7.1f, 5.6f, 8.4f, -0.4f, 2.1f)
            curveToRelative(-4.4f, -4.6f, -5.2f, -5.0f, -9.4f, -5.0f)
            curveToRelative(-3.2f, 0.0f, -5.4f, 0.7f, -7.4f, 2.2f)
            curveToRelative(-9.4f, 7.3f, -11.1f, 15.6f, -12.4f, 61.3f)
            curveToRelative(-0.9f, 31.9f, -1.9f, 43.8f, -3.9f, 45.8f)
            curveToRelative(-0.5f, 0.5f, -1.4f, -1.2f, -2.1f, -4.5f)
            close()
        }

        // Segment 2
        path(
            fill = SolidColor(segColor(2)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(225.2f, 2410.8f)
            curveToRelative(-3.0f, -5.8f, -10.2f, -14.0f, -11.6f, -13.5f)
            curveToRelative(-0.7f, 0.3f, -5.4f, -7.6f, -11.7f, -19.6f)
            curveToRelative(-5.7f, -11.1f, -16.4f, -31.6f, -23.7f, -45.7f)
            curveToRelative(-18.5f, -35.7f, -31.8f, -63.5f, -40.1f, -84.0f)
            curveToRelative(-3.9f, -9.6f, -13.2f, -29.7f, -20.7f, -44.6f)
            curveToRelative(-15.1f, -30.0f, -16.2f, -33.7f, -12.5f, -39.7f)
            curveToRelative(1.2f, -1.9f, 4.5f, -5.3f, 7.4f, -7.4f)
            curveToRelative(4.7f, -3.6f, 39.0f, -25.3f, 44.5f, -28.2f)
            curveToRelative(1.9f, -1.0f, 6.7f, 0.0f, 31.9f, 6.4f)
            curveToRelative(46.0f, 11.8f, 50.6f, 13.7f, 51.8f, 21.3f)
            curveToRelative(0.3f, 2.0f, 1.5f, 7.5f, 2.6f, 12.2f)
            curveToRelative(7.1f, 29.6f, 39.8f, 114.9f, 64.4f, 168.0f)
            curveToRelative(7.2f, 15.6f, 8.5f, 17.9f, 18.3f, 34.3f)
            curveToRelative(3.6f, 5.9f, 6.3f, 11.0f, 6.0f, 11.3f)
            curveToRelative(-0.3f, 0.3f, -4.1f, 0.0f, -8.5f, -0.6f)
            curveToRelative(-4.3f, -0.6f, -16.3f, -1.3f, -26.8f, -1.7f)
            curveToRelative(-22.7f, -0.7f, -27.7f, 0.1f, -36.6f, 5.9f)
            curveToRelative(-11.0f, 7.1f, -23.7f, 20.7f, -17.1f, 18.4f)
            curveToRelative(1.9f, -0.7f, 1.6f, -0.4f, -7.7f, 7.2f)
            curveToRelative(-2.9f, 2.3f, -5.8f, 4.2f, -6.4f, 4.2f)
            curveToRelative(-0.7f, 0.0f, -2.2f, -1.9f, -3.5f, -4.2f)
            close()
        }
        // SEGMENT 3
        path(
            fill = SolidColor(segColor(3)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(91.6f, 2142.9f)
            curveToRelative(-0.2f, -0.8f, -0.9f, -4.8f, -1.5f, -8.9f)
            curveToRelative(-1.4f, -9.9f, -6.3f, -27.7f, -16.6f, -60.0f)
            curveToRelative(-11.3f, -35.3f, -16.8f, -54.5f, -28.1f, -98.0f)
            curveToRelative(-5.3f, -20.1f, -11.1f, -41.4f, -13.0f, -47.5f)
            curveToRelative(-5.1f, -16.1f, -10.2f, -36.2f, -10.9f, -43.1f)
            lineToRelative(-0.7f, -5.9f)
            lineToRelative(23.3f, -10.9f)
            curveToRelative(12.9f, -6.0f, 28.5f, -12.8f, 34.7f, -15.3f)
            curveToRelative(13.0f, -5.1f, 9.8f, -5.4f, 33.2f, 3.4f)
            curveToRelative(18.8f, 7.1f, 45.8f, 18.3f, 58.5f, 24.2f)
            lineToRelative(10.1f, 4.8f)
            lineToRelative(1.7f, 16.1f)
            curveToRelative(3.5f, 32.2f, 10.7f, 78.9f, 13.2f, 84.8f)
            curveToRelative(2.6f, 6.2f, 3.7f, 4.2f, 3.1f, -5.3f)
            curveToRelative(-0.5f, -7.3f, 0.4f, -3.5f, 5.8f, 22.2f)
            curveToRelative(3.5f, 17.1f, 6.9f, 32.9f, 7.4f, 35.2f)
            curveToRelative(1.2f, 5.0f, -0.1f, 4.8f, -1.8f, -0.2f)
            curveToRelative(-1.4f, -4.1f, -3.0f, -4.6f, -3.0f, -0.9f)
            curveToRelative(0.0f, 4.0f, 3.7f, 21.5f, 8.1f, 37.9f)
            curveToRelative(2.2f, 8.3f, 5.4f, 20.0f, 7.0f, 26.0f)
            curveToRelative(3.2f, 12.3f, 3.7f, 18.6f, 1.4f, 20.5f)
            curveToRelative(-2.2f, 1.8f, -7.4f, 0.8f, -28.5f, -5.4f)
            curveToRelative(-24.6f, 7.4f, -30.4f, 8.3f, -40.5f, -6.5f)
            curveToRelative(-4.3f, 0.7f, -8.3f, 1.9f, -9.0f, 2.5f)
            curveToRelative(-0.6f, 0.7f, -5.0f, 3.1f, -9.6f, 5.4f)
            curveToRelative(-10.6f, 5.4f, -19.4f, 11.3f, -21.6f, 14.6f)
            curveToRelative(-2.2f, 3.4f, -5.7f, 5.1f, -12.6f, 5.9f)
            curveToRelative(-4.4f, 0.6f, -6.2f, 1.3f, -7.7f, 3.2f)
            curveToRelative(-1.4f, 1.8f, -2.1f, 2.1f, -2.4f, 1.2f)
            close()

            moveTo(206.7f, 2024.3f)
            curveToRelative(-0.6f, -7.2f, -3.7f, -18.3f, -5.0f, -18.3f)
            curveToRelative(-1.5f, 0.0f, -0.2f, 15.0f, 1.8f, 19.8f)
            curveToRelative(2.5f, 6.0f, 3.9f, 5.4f, 3.2f, -1.5f)
            close()
        }
        // SEGMENT 4
        path(
            fill = SolidColor(segColor(4)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(163.5f, 1854.8f)
            curveToRelative(-1.6f, -0.6f, -11.0f, -4.1f, -20.8f, -8.0f)
            curveToRelative(-9.7f, -3.8f, -20.2f, -7.4f, -23.3f, -8.0f)
            curveToRelative(-4.2f, -0.8f, -5.8f, -1.7f, -6.6f, -3.4f)
            curveToRelative(-1.1f, -2.3f, -6.0f, -4.9f, -11.8f, -6.0f)
            curveToRelative(-1.9f, -0.3f, -4.1f, -1.1f, -4.8f, -1.6f)
            curveToRelative(-2.4f, -2.0f, -10.2f, 0.0f, -21.4f, 5.5f)
            curveToRelative(-16.7f, 8.1f, -32.0f, 14.5f, -40.6f, 16.8f)
            curveToRelative(-4.3f, 1.2f, -8.9f, 2.7f, -10.4f, 3.4f)
            curveToRelative(-3.5f, 1.7f, -2.8f, 4.7f, -7.8f, -33.0f)
            curveToRelative(-7.3f, -55.2f, -7.9f, -62.3f, -12.5f, -151.0f)
            curveToRelative(-3.4f, -64.5f, -3.8f, -72.9f, -2.8f, -69.2f)
            curveToRelative(0.7f, 2.7f, 5.3f, 2.1f, 10.9f, -1.5f)
            lineToRelative(4.9f, -3.3f)
            lineToRelative(-5.5f, -0.5f)
            lineToRelative(-5.5f, -0.5f)
            lineToRelative(9.0f, -2.4f)
            curveToRelative(18.8f, -5.0f, 60.3f, -17.1f, 68.1f, -19.8f)
            curveToRelative(6.6f, -2.4f, 8.1f, -2.6f, 8.0f, -1.4f)
            curveToRelative(-0.1f, 1.9f, 4.4f, 4.2f, 9.7f, 4.9f)
            curveToRelative(3.9f, 0.5f, 4.0f, 0.4f, 3.5f, -2.1f)
            curveToRelative(-0.5f, -2.5f, -0.4f, -2.5f, 5.3f, 1.6f)
            curveToRelative(3.3f, 2.2f, 5.4f, 4.4f, 4.9f, 4.7f)
            curveToRelative(-1.4f, 0.8f, 3.8f, 5.6f, 14.6f, 13.3f)
            curveToRelative(12.3f, 8.8f, 19.4f, 12.7f, 23.3f, 12.7f)
            curveToRelative(1.8f, 0.0f, 3.0f, -0.4f, 2.6f, -1.0f)
            curveToRelative(-0.3f, -0.5f, 1.9f, 0.5f, 4.9f, 2.3f)
            lineToRelative(5.5f, 3.2f)
            lineToRelative(-3.5f, 0.3f)
            curveToRelative(-1.9f, 0.2f, -3.4f, 0.7f, -3.4f, 1.1f)
            curveToRelative(0.0f, 0.4f, 1.8f, 2.2f, 4.0f, 3.9f)
            curveToRelative(2.5f, 2.1f, 4.3f, 4.6f, 5.0f, 7.0f)
            curveToRelative(1.9f, 6.9f, 3.2f, 31.6f, 2.8f, 56.2f)
            curveToRelative(-0.5f, 42.3f, 2.8f, 147.4f, 5.4f, 165.7f)
            curveToRelative(0.6f, 4.5f, 0.8f, 8.8f, 0.5f, 9.7f)
            curveToRelative(-0.7f, 1.9f, -7.4f, 2.1f, -12.2f, 0.4f)
            close()
        }
        // SEGMENT 5
        path(
            fill = SolidColor(segColor(5)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(166.0f, 1593.0f)
            curveToRelative(-5.8f, -3.9f, -14.9f, -9.6f, -20.2f, -12.8f)
            curveToRelative(-6.3f, -3.6f, -9.8f, -6.3f, -9.8f, -7.4f)
            curveToRelative(0.0f, -1.7f, -3.4f, -4.3f, -18.8f, -14.2f)
            curveToRelative(-9.5f, -6.2f, -14.5f, -7.9f, -22.2f, -7.4f)
            curveToRelative(-4.9f, 0.3f, -5.2f, 0.4f, -2.5f, 1.2f)
            curveToRelative(5.9f, 1.5f, 5.9f, 1.9f, 0.6f, 2.8f)
            curveToRelative(-4.4f, 0.7f, -33.1f, 9.0f, -40.0f, 11.6f)
            curveToRelative(-1.4f, 0.5f, -1.4f, 0.4f, -0.1f, -1.3f)
            curveToRelative(1.4f, -1.7f, 1.3f, -1.8f, -3.0f, -1.1f)
            curveToRelative(-5.4f, 0.8f, -20.0f, 5.4f, -21.8f, 6.9f)
            curveToRelative(-1.1f, 0.9f, -1.1f, 1.2f, 0.5f, 1.8f)
            curveToRelative(2.5f, 0.9f, -11.7f, 3.4f, -14.8f, 2.6f)
            curveToRelative(-3.8f, -1.0f, -5.0f, -4.2f, -6.1f, -15.6f)
            curveToRelative(-1.5f, -16.1f, -0.5f, -39.2f, 2.6f, -54.6f)
            curveToRelative(4.5f, -23.1f, 4.8f, -25.7f, 5.1f, -43.5f)
            curveToRelative(0.4f, -19.1f, 0.3f, -18.1f, 10.5f, -77.0f)
            curveToRelative(6.0f, -34.7f, 12.9f, -67.2f, 14.6f, -68.9f)
            curveToRelative(0.2f, -0.3f, 24.8f, -3.5f, 54.7f, -7.2f)
            curveToRelative(29.8f, -3.7f, 55.7f, -7.0f, 57.4f, -7.3f)
            curveToRelative(3.1f, -0.4f, 4.8f, 0.9f, 31.2f, 24.9f)
            curveToRelative(15.3f, 14.0f, 30.5f, 27.8f, 33.6f, 30.7f)
            lineToRelative(5.8f, 5.4f)
            lineToRelative(-2.2f, 12.4f)
            curveToRelative(-2.4f, 14.0f, -4.8f, 24.5f, -5.0f, 21.6f)
            curveToRelative(-0.1f, -1.8f, -0.2f, -1.9f, -2.1f, -0.1f)
            curveToRelative(-1.1f, 1.0f, -2.7f, 4.1f, -3.5f, 6.8f)
            curveToRelative(-1.6f, 5.3f, -2.1f, 16.2f, -0.7f, 15.3f)
            curveToRelative(0.4f, -0.2f, -0.9f, 6.4f, -3.0f, 14.7f)
            curveToRelative(-10.1f, 40.2f, -22.6f, 112.0f, -22.9f, 131.7f)
            curveToRelative(0.0f, 3.2f, -0.3f, 3.9f, -0.8f, 2.6f)
            curveToRelative(-0.7f, -1.8f, -0.8f, -1.8f, -1.4f, 0.5f)
            curveToRelative(-1.0f, 3.6f, -0.8f, 10.4f, 0.3f, 13.3f)
            curveToRelative(1.7f, 4.5f, -1.9f, 18.6f, -4.8f, 18.6f)
            curveToRelative(-0.4f, 0.0f, -5.4f, -3.2f, -11.2f, -7.0f)
            close()
        }
        // SEGMENT 6
        path(
            fill = SolidColor(segColor(6)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(220.1f, 1334.5f)
            curveToRelative(-12.8f, -9.5f, -28.3f, -24.4f, -33.6f, -32.4f)
            curveToRelative(-2.2f, -3.3f, -4.3f, -6.1f, -4.7f, -6.1f)
            curveToRelative(-0.3f, 0.0f, -1.0f, 1.2f, -1.5f, 2.6f)
            lineToRelative(-0.9f, 2.7f)
            lineToRelative(-4.8f, -5.6f)
            curveToRelative(-2.6f, -3.0f, -6.1f, -6.0f, -7.8f, -6.6f)
            curveToRelative(-5.9f, -2.1f, -46.2f, -0.6f, -54.3f, 1.9f)
            curveToRelative(-1.6f, 0.5f, -14.2f, 2.3f, -27.9f, 4.0f)
            curveToRelative(-23.2f, 2.8f, -33.3f, 4.6f, -38.0f, 6.5f)
            curveToRelative(-1.7f, 0.8f, -1.9f, 0.4f, -1.4f, -3.6f)
            curveToRelative(0.3f, -2.5f, 2.1f, -8.1f, 4.2f, -12.6f)
            curveToRelative(5.4f, -12.1f, 13.4f, -34.9f, 26.1f, -74.3f)
            curveToRelative(11.6f, -36.2f, 13.0f, -40.0f, 25.5f, -71.5f)
            curveToRelative(12.6f, -31.9f, 22.0f, -54.5f, 31.3f, -75.2f)
            lineToRelative(9.1f, -20.3f)
            lineToRelative(42.6f, 0.0f)
            curveToRelative(39.8f, 0.0f, 75.8f, 1.2f, 80.1f, 2.5f)
            curveToRelative(1.1f, 0.4f, 1.9f, 1.2f, 1.9f, 1.9f)
            curveToRelative(0.0f, 0.6f, 2.8f, 4.1f, 6.3f, 7.6f)
            curveToRelative(3.4f, 3.6f, 12.5f, 14.3f, 20.2f, 23.9f)
            curveToRelative(7.7f, 9.6f, 17.5f, 21.4f, 21.8f, 26.3f)
            curveToRelative(4.2f, 4.8f, 7.7f, 9.5f, 7.7f, 10.4f)
            curveToRelative(0.0f, 4.4f, -11.0f, 21.3f, -14.7f, 22.5f)
            curveToRelative(-0.7f, 0.2f, -2.1f, 3.8f, -3.2f, 8.0f)
            curveToRelative(-1.2f, 4.6f, -5.5f, 14.7f, -10.9f, 25.5f)
            curveToRelative(-4.9f, 9.8f, -11.3f, 24.0f, -14.2f, 31.4f)
            curveToRelative(-5.9f, 15.1f, -9.2f, 20.4f, -12.0f, 19.5f)
            curveToRelative(-1.5f, -0.5f, -1.7f, -0.1f, -1.3f, 3.7f)
            curveToRelative(0.8f, 6.7f, -2.1f, 16.2f, -20.0f, 64.8f)
            curveToRelative(-10.3f, 28.1f, -13.8f, 38.8f, -14.5f, 44.4f)
            lineToRelative(-0.7f, 5.7f)
            lineToRelative(-10.4f, -7.6f)
            close()
        }

        // SEGMENT 7
        path(
            fill = SolidColor(segColor(7)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(331.5f, 1090.2f)
            curveToRelative(-5.1f, -3.2f, -19.3f, -18.9f, -35.5f, -39.5f)
            lineToRelative(-15.5f, -19.6f)
            lineToRelative(-7.5f, -0.2f)
            curveToRelative(-4.1f, -0.1f, -13.8f, -0.5f, -21.5f, -0.8f)
            lineToRelative(-14.0f, -0.8f)
            lineToRelative(13.0f, -0.2f)
            curveToRelative(11.1f, -0.3f, 12.4f, -0.5f, 8.5f, -1.3f)
            curveToRelative(-2.6f, -0.5f, -10.5f, -0.6f, -18.5f, -0.2f)
            curveToRelative(-7.7f, 0.4f, -19.4f, 0.6f, -26.0f, 0.6f)
            curveToRelative(-23.1f, -0.3f, -62.7f, -4.0f, -64.8f, -6.1f)
            curveToRelative(-1.6f, -1.6f, -0.1f, -5.2f, 5.8f, -14.1f)
            curveToRelative(3.1f, -4.7f, 10.3f, -18.0f, 16.0f, -29.5f)
            curveToRelative(5.7f, -11.6f, 14.7f, -28.0f, 20.0f, -36.4f)
            curveToRelative(5.3f, -8.5f, 13.2f, -22.4f, 17.4f, -31.0f)
            curveToRelative(11.6f, -23.2f, 24.4f, -43.4f, 34.1f, -53.6f)
            curveToRelative(2.6f, -2.7f, 5.3f, -6.6f, 6.0f, -8.5f)
            curveToRelative(2.7f, -7.3f, 18.4f, -31.6f, 32.7f, -50.7f)
            curveToRelative(7.5f, -10.1f, 8.7f, -11.3f, 11.5f, -11.3f)
            curveToRelative(1.7f, 0.0f, 25.6f, 3.8f, 53.2f, 8.4f)
            curveToRelative(27.6f, 4.6f, 54.6f, 9.2f, 60.1f, 10.0f)
            curveToRelative(18.0f, 3.0f, 20.7f, 5.4f, 33.5f, 29.6f)
            curveToRelative(4.5f, 8.5f, 11.5f, 21.4f, 15.5f, 28.5f)
            curveToRelative(7.5f, 13.4f, 10.5f, 20.6f, 10.5f, 25.2f)
            curveToRelative(0.0f, 3.0f, -1.2f, 4.9f, -25.9f, 38.8f)
            curveToRelative(-21.2f, 29.2f, -32.6f, 45.9f, -37.1f, 54.4f)
            curveToRelative(-2.2f, 4.1f, -4.6f, 7.1f, -6.7f, 8.4f)
            curveToRelative(-2.3f, 1.3f, -3.6f, 3.0f, -4.3f, 5.6f)
            curveToRelative(-1.2f, 4.5f, -4.1f, 8.7f, -14.3f, 20.5f)
            curveToRelative(-4.2f, 4.9f, -7.4f, 9.3f, -7.1f, 10.0f)
            curveToRelative(0.2f, 0.6f, -2.5f, 6.3f, -6.2f, 12.6f)
            curveToRelative(-9.6f, 16.6f, -15.0f, 27.0f, -16.4f, 31.3f)
            curveToRelative(-0.6f, 2.0f, -2.4f, 4.9f, -3.9f, 6.5f)
            curveToRelative(-4.2f, 4.1f, -6.4f, 8.0f, -5.7f, 9.8f)
            curveToRelative(0.6f, 1.5f, -1.8f, 5.4f, -3.3f, 5.4f)
            curveToRelative(-0.3f, 0.0f, -1.9f, -0.8f, -3.6f, -1.8f)
            close()
        }

        // Segment 8
        path(
            fill = SolidColor(segColor(8)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(479.6f, 866.1f)
            curveToRelative(-1.4f, -2.2f, -8.0f, -14.0f, -14.7f, -26.3f)
            curveToRelative(-17.9f, -32.8f, -25.3f, -43.8f, -30.9f, -46.0f)
            curveToRelative(-4.2f, -1.6f, -14.7f, -3.5f, -37.0f, -6.8f)
            curveToRelative(-34.8f, -5.1f, -88.7f, -15.3f, -90.6f, -17.3f)
            curveToRelative(-4.0f, -4.0f, 56.0f, -80.5f, 111.5f, -142.0f)
            curveToRelative(21.5f, -23.9f, 69.2f, -72.9f, 70.5f, -72.4f)
            curveToRelative(0.6f, 0.2f, 20.0f, 6.6f, 43.1f, 14.2f)
            curveToRelative(74.2f, 24.3f, 92.9f, 31.3f, 97.2f, 36.9f)
            curveToRelative(2.5f, 3.1f, 6.6f, 15.3f, 15.2f, 44.6f)
            curveToRelative(3.3f, 11.3f, 7.3f, 24.8f, 8.9f, 30.1f)
            lineToRelative(2.9f, 9.5f)
            lineToRelative(-22.9f, 22.0f)
            curveToRelative(-26.5f, 25.3f, -64.3f, 63.5f, -87.2f, 87.9f)
            curveToRelative(-13.8f, 14.7f, -52.6f, 58.3f, -59.4f, 66.6f)
            curveToRelative(-1.3f, 1.6f, -2.8f, 2.9f, -3.3f, 2.9f)
            curveToRelative(-0.5f, 0.0f, -2.0f, -1.8f, -3.3f, -3.9f)
            close()
        }
        // Segment 9
        path(
            fill = SolidColor(segColor(9)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(671.7f, 671.3f)
            curveToRelative(-1.6f, -1.9f, -6.9f, -18.4f, -18.5f, -58.2f)
            lineToRelative(-6.8f, -23.4f)
            lineToRelative(-59.9f, -20.2f)
            curveToRelative(-64.9f, -21.9f, -80.5f, -27.4f, -80.5f, -28.7f)
            curveToRelative(0.0f, -2.3f, 34.4f, -34.0f, 70.5f, -64.9f)
            curveToRelative(46.5f, -39.8f, 83.0f, -69.2f, 101.5f, -81.6f)
            curveToRelative(4.2f, -2.8f, 10.0f, -7.2f, 13.0f, -9.8f)
            curveToRelative(5.3f, -4.5f, 32.2f, -22.8f, 40.6f, -27.6f)
            lineToRelative(4.1f, -2.3f)
            lineToRelative(51.9f, 26.6f)
            curveToRelative(28.5f, 14.6f, 59.4f, 30.5f, 68.6f, 35.4f)
            lineToRelative(16.6f, 8.8f)
            lineToRelative(1.1f, 7.6f)
            curveToRelative(2.5f, 17.8f, 8.1f, 77.9f, 8.1f, 86.6f)
            curveToRelative(0.0f, 3.2f, -0.8f, 3.9f, -14.2f, 13.5f)
            curveToRelative(-7.9f, 5.6f, -16.7f, 11.3f, -19.6f, 12.6f)
            curveToRelative(-3.0f, 1.3f, -14.2f, 8.8f, -25.0f, 16.6f)
            curveToRelative(-10.9f, 7.9f, -28.9f, 20.8f, -40.2f, 28.7f)
            curveToRelative(-31.7f, 22.2f, -41.8f, 29.9f, -72.0f, 54.5f)
            curveToRelative(-22.0f, 17.9f, -29.4f, 23.6f, -33.7f, 25.9f)
            curveToRelative(-3.9f, 2.0f, -3.7f, 2.0f, -5.6f, -0.1f)
            close()
        }

        // Segment 10
        path(
            fill = SolidColor(segColor(10)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(901.6f, 505.8f)
            curveToRelative(-0.4f, -2.4f, -1.5f, -17.3f, -2.7f, -33.3f)
            curveToRelative(-3.0f, -44.0f, -4.9f, -54.9f, -10.2f, -58.9f)
            curveToRelative(-5.4f, -4.1f, -29.0f, -16.7f, -83.2f, -44.3f)
            curveToRelative(-28.6f, -14.6f, -52.4f, -26.9f, -52.8f, -27.3f)
            curveToRelative(-1.7f, -1.5f, 77.7f, -50.5f, 124.3f, -76.7f)
            curveToRelative(37.7f, -21.2f, 119.1f, -62.9f, 128.3f, -65.7f)
            curveToRelative(7.9f, -2.5f, 11.2f, -1.6f, 20.1f, 5.2f)
            curveToRelative(11.7f, 8.8f, 33.9f, 25.5f, 83.7f, 62.4f)
            curveToRelative(21.6f, 16.1f, 26.9f, 21.4f, 29.5f, 29.6f)
            curveToRelative(2.1f, 6.9f, 1.6f, 16.7f, -2.6f, 51.7f)
            curveToRelative(-4.4f, 36.2f, -6.7f, 46.3f, -11.7f, 50.7f)
            curveToRelative(-1.0f, 1.0f, -9.5f, 5.1f, -18.9f, 9.1f)
            curveToRelative(-43.2f, 18.8f, -109.7f, 51.7f, -164.2f, 81.2f)
            curveToRelative(-19.9f, 10.8f, -36.9f, 19.8f, -37.7f, 20.1f)
            curveToRelative(-1.0f, 0.4f, -1.5f, -0.6f, -1.9f, -3.8f)
            close()
        }

        // segment 11
        path(
            fill = SolidColor(segColor(11)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(1153.0f, 387.0f)
            curveToRelative(-1.1f, -2.0f, 0.8f, -25.6f, 6.4f, -80.0f)
            curveToRelative(1.3f, -12.4f, 2.2f, -22.8f, 2.0f, -23.1f)
            curveToRelative(-0.2f, -0.4f, -14.9f, -11.4f, -32.6f, -24.5f)
            curveToRelative(-68.3f, -50.4f, -95.8f, -71.5f, -95.8f, -73.5f)
            curveToRelative(0.0f, -2.4f, 40.5f, -20.1f, 92.5f, -40.2f)
            curveToRelative(45.0f, -17.5f, 69.4f, -26.1f, 130.0f, -46.2f)
            curveToRelative(56.6f, -18.8f, 58.1f, -19.2f, 61.4f, -17.9f)
            curveToRelative(5.0f, 2.1f, 10.9f, 7.2f, 26.6f, 22.9f)
            curveToRelative(13.3f, 13.4f, 74.8f, 77.2f, 84.9f, 88.2f)
            lineToRelative(4.8f, 5.2f)
            lineToRelative(-10.7f, 33.3f)
            curveToRelative(-13.6f, 42.1f, -20.9f, 63.5f, -24.7f, 71.9f)
            curveToRelative(-2.8f, 6.3f, -3.3f, 6.9f, -8.6f, 9.2f)
            curveToRelative(-7.3f, 3.2f, -12.9f, 4.9f, -28.2f, 8.7f)
            curveToRelative(-28.3f, 7.1f, -54.6f, 14.5f, -73.0f, 20.5f)
            curveToRelative(-10.7f, 3.5f, -25.1f, 8.2f, -32.0f, 10.4f)
            curveToRelative(-6.9f, 2.1f, -20.1f, 6.8f, -29.5f, 10.3f)
            curveToRelative(-9.3f, 3.6f, -26.7f, 9.9f, -38.5f, 14.1f)
            curveToRelative(-11.8f, 4.2f, -24.1f, 8.7f, -27.3f, 10.1f)
            curveToRelative(-6.9f, 3.1f, -6.4f, 3.1f, -7.7f, 0.6f)
            close()
        }

        // segment 12
        path(
            fill = SolidColor(segColor(12)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(1417.0f, 304.8f)
            curveToRelative(0.0f, -0.7f, 8.1f, -26.0f, 17.9f, -56.2f)
            lineToRelative(17.9f, -54.9f)
            lineToRelative(-44.0f, -45.1f)
            curveToRelative(-40.6f, -41.5f, -62.1f, -64.6f, -66.3f, -71.0f)
            curveToRelative(-3.3f, -5.0f, -3.0f, -5.2f, 20.0f, -10.6f)
            curveToRelative(7.2f, -1.6f, 20.7f, -5.0f, 30.0f, -7.5f)
            curveToRelative(50.4f, -13.4f, 207.0f, -42.1f, 241.1f, -44.2f)
            lineToRelative(10.0f, -0.6f)
            lineToRelative(6.4f, 7.1f)
            curveToRelative(9.3f, 10.4f, 24.2f, 31.3f, 51.1f, 71.5f)
            curveToRelative(23.6f, 35.2f, 30.6f, 47.1f, 32.9f, 55.6f)
            curveToRelative(1.1f, 4.3f, 1.1f, 4.3f, -6.5f, 18.4f)
            curveToRelative(-4.3f, 7.8f, -18.3f, 33.2f, -31.4f, 56.4f)
            curveToRelative(-18.4f, 32.9f, -24.0f, 42.3f, -25.5f, 42.3f)
            curveToRelative(-1.9f, 0.0f, -1.9f, 0.0f, -0.1f, -1.3f)
            curveToRelative(1.1f, -0.9f, 1.5f, -1.9f, 1.0f, -2.7f)
            curveToRelative(-1.3f, -2.2f, -18.5f, 0.7f, -18.5f, 3.1f)
            curveToRelative(0.0f, 0.5f, 2.8f, 1.0f, 6.3f, 1.0f)
            curveToRelative(3.9f, 0.1f, -1.7f, 1.0f, -15.3f, 2.4f)
            curveToRelative(-47.1f, 5.1f, -158.7f, 23.5f, -206.1f, 34.0f)
            curveToRelative(-16.8f, 3.8f, -20.9f, 4.2f, -20.9f, 2.3f)
            close()
        }
        // segment 13
        path(
            fill = SolidColor(segColor(13)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(1695.5f, 264.1f)
            curveToRelative(-1.2f, -2.1f, 9.7f, -24.9f, 26.7f, -55.5f)
            curveToRelative(20.1f, -36.2f, 30.8f, -58.1f, 30.8f, -62.7f)
            curveToRelative(0.0f, -1.9f, -11.5f, -20.2f, -34.0f, -53.9f)
            curveToRelative(-6.4f, -9.6f, -12.3f, -18.6f, -13.0f, -20.0f)
            lineToRelative(-1.3f, -2.5f)
            lineToRelative(2.7f, 2.4f)
            curveToRelative(1.9f, 1.8f, 2.6f, 2.0f, 2.6f, 1.0f)
            curveToRelative(0.0f, -3.2f, -18.1f, -29.9f, -20.2f, -29.9f)
            curveToRelative(-0.6f, 0.0f, -0.8f, 0.7f, -0.4f, 1.5f)
            curveToRelative(1.9f, 5.0f, -2.5f, -1.4f, -13.1f, -19.0f)
            curveToRelative(-8.3f, -13.8f, -8.2f, -13.2f, -2.0f, -14.4f)
            curveToRelative(37.6f, -7.6f, 225.1f, -13.1f, 285.1f, -8.3f)
            lineToRelative(21.9f, 1.7f)
            lineToRelative(19.7f, 46.5f)
            curveToRelative(23.7f, 56.3f, 34.8f, 83.7f, 35.6f, 88.2f)
            curveToRelative(0.4f, 2.1f, 0.2f, 2.8f, -0.3f, 2.0f)
            curveToRelative(-1.1f, -1.5f, -2.0f, -0.6f, -13.4f, 14.3f)
            curveToRelative(-4.6f, 6.1f, -14.5f, 18.4f, -21.9f, 27.5f)
            curveToRelative(-14.3f, 17.5f, -26.1f, 32.5f, -27.5f, 35.0f)
            curveToRelative(-0.4f, 0.8f, 2.5f, -1.6f, 6.5f, -5.5f)
            curveToRelative(4.0f, -3.8f, 6.9f, -6.3f, 6.4f, -5.5f)
            curveToRelative(-1.1f, 2.1f, -29.5f, 36.0f, -30.1f, 36.0f)
            curveToRelative(-0.6f, 0.0f, 0.6f, -1.9f, 3.7f, -6.0f)
            curveToRelative(1.5f, -1.9f, 3.3f, -4.6f, 3.9f, -6.0f)
            curveToRelative(2.2f, -4.4f, -13.2f, 12.3f, -18.2f, 19.8f)
            lineToRelative(-4.8f, 7.2f)
            lineToRelative(-10.7f, 0.0f)
            curveToRelative(-5.9f, 0.0f, -46.5f, 0.7f, -90.2f, 1.5f)
            curveToRelative(-82.6f, 1.5f, -125.7f, 3.0f, -132.5f, 4.5f)
            curveToRelative(-5.2f, 1.2f, -11.2f, 1.2f, -12.0f, 0.1f)
            close()

            moveTo(1889.5f, 256.0f)
            curveToRelative(-2.7f, -0.5f, -17.6f, -0.8f, -33.0f, -0.8f)
            curveToRelative(-15.4f, 0.0f, -30.2f, 0.3f, -33.0f, 0.8f)
            curveToRelative(-2.9f, 0.4f, 11.2f, 0.7f, 33.0f, 0.7f)
            curveToRelative(21.8f, 0.0f, 35.9f, -0.3f, 33.0f, -0.7f)
            close()
        }

        // Segment 14
        path(
            fill = SolidColor(segColor(14)),
            stroke = null,
            strokeLineWidth = 0.0f,
            strokeLineCap = Butt,
            strokeLineJoin = Miter,
            strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(2216.6f, 289.1f)
            curveToRelative(-1.8f, -2.9f, -102.0f, -15.8f, -175.6f, -22.6f)
            curveToRelative(-35.0f, -3.3f, -59.3f, -6.2f, -69.6f, -8.3f)
            lineToRelative(-8.1f, -1.7f)
            lineToRelative(17.6f, -21.0f)
            curveToRelative(9.7f, -11.6f, 30.5f, -36.6f, 46.2f, -55.6f)
            lineToRelative(28.6f, -34.6f)
            lineToRelative(-2.5f, -6.4f)
            curveToRelative(-3.5f, -9.0f, -27.9f, -68.4f, -43.4f, -105.7f)
            curveToRelative(-7.2f, -17.2f, -12.9f, -31.4f, -12.7f, -31.6f)
            curveToRelative(0.9f, -0.7f, 61.6f, 4.0f, 95.9f, 7.5f)
            curveToRelative(96.6f, 9.6f, 197.6f, 26.0f, 222.8f, 36.2f)
            curveToRelative(3.0f, 1.2f, 6.3f, 3.3f, 7.4f, 4.7f)
            curveToRelative(2.4f, 3.3f, 6.9f, 21.3f, 10.8f, 43.1f)
            curveToRelative(4.3f, 24.6f, 7.7f, 41.4f, 11.2f, 56.4f)
            curveToRelative(1.9f, 8.3f, 3.0f, 15.6f, 3.0f, 20.3f)
            curveToRelative(0.0f, 14.6f, -7.9f, 24.8f, -37.7f, 48.7f)
            curveToRelative(-16.8f, 13.4f, -31.7f, 26.1f, -43.9f, 37.2f)
            curveToRelative(-9.3f, 8.5f, -24.0f, 20.0f, -36.5f, 28.5f)
            curveToRelative(-7.9f, 5.4f, -12.2f, 7.0f, -13.5f, 4.9f)
            close()
        }
    }.build()
}

@Preview
@Composable
private fun PreviewRpm() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = rpm(15), contentDescription = "")
    }
}
