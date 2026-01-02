package com.codaers.f1racehud.f1car

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val F1Sidepods: ImageVector
    get() {
        if (f1sidepods != null) {
            return f1sidepods!!
        }
        f1sidepods = Builder(name = "F1original", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {


            path(fill = SolidColor(Color.Red), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(402.8f, 980.2f)


                //Left
                moveTo(270.0f, 819.7f)
                curveToRelative(-1.2f, -1.0f, -3.0f, -2.5f, -4.1f, -3.3f)
                curveToRelative(-2.8f, -2.0f, -5.1f, -5.6f, -5.3f, -8.5f)
                curveToRelative(-0.6f, -5.9f, 3.7f, -10.6f, 8.4f, -9.3f)
                curveToRelative(2.3f, 0.6f, 5.7f, 3.0f, 18.2f, 13.1f)
                curveToRelative(5.7f, 4.6f, 10.4f, 8.3f, 10.4f, 8.3f)
                curveToRelative(0.1f, 0.0f, 0.2f, -0.6f, 0.3f, -1.3f)
                curveToRelative(0.1f, -1.8f, 1.0f, -10.4f, 1.7f, -15.4f)
                curveToRelative(1.6f, -12.3f, 5.4f, -26.5f, 10.0f, -37.8f)
                curveToRelative(9.3f, -22.5f, 13.5f, -35.0f, 25.3f, -74.5f)
                curveToRelative(1.6f, -5.6f, 2.8f, -8.1f, 5.1f, -10.6f)
                lineToRelative(1.9f, -2.1f)
                lineToRelative(-0.6f, -3.5f)
                curveToRelative(-0.3f, -2.0f, -0.6f, -4.1f, -0.6f, -4.8f)
                curveToRelative(0.0f, -1.0f, 2.0f, -6.3f, 5.3f, -13.3f)
                curveToRelative(1.5f, -3.2f, 6.5f, -23.8f, 10.3f, -41.8f)
                curveToRelative(1.3f, -6.2f, 11.1f, -50.1f, 13.6f, -60.8f)
                curveToRelative(6.6f, -28.5f, 7.2f, -33.8f, 5.9f, -52.0f)
                curveToRelative(-0.3f, -4.1f, -0.8f, -11.2f, -1.0f, -15.8f)
                curveToRelative(-0.6f, -9.3f, -1.6f, -18.1f, -3.0f, -27.9f)
                curveToRelative(-0.6f, -3.7f, -1.0f, -7.0f, -1.0f, -7.4f)
                curveToRelative(0.0f, -0.4f, -1.5f, -1.4f, -3.3f, -2.2f)
                curveToRelative(-6.8f, -3.0f, -8.9f, -5.8f, -14.1f, -17.3f)
                curveToRelative(-3.4f, -7.6f, -3.5f, -7.8f, -12.1f, -17.8f)
                curveToRelative(-7.1f, -8.3f, -13.1f, -16.4f, -16.3f, -22.0f)
                curveToRelative(-5.2f, -9.1f, -6.3f, -14.3f, -9.1f, -41.0f)
                curveToRelative(-0.6f, -6.8f, -1.5f, -13.9f, -1.7f, -16.0f)
                curveToRelative(-0.8f, -6.8f, -4.4f, -23.0f, -5.7f, -25.3f)
                curveToRelative(-0.3f, -0.6f, -2.3f, -2.9f, -4.3f, -5.1f)
                curveToRelative(-10.6f, -10.9f, -25.4f, -27.6f, -26.0f, -29.4f)
                curveToRelative(-0.3f, -0.9f, -0.9f, -1.3f, -2.0f, -1.3f)
                lineToRelative(-1.6f, 0.0f)
                lineToRelative(0.3f, 4.8f)
                curveToRelative(0.2f, 2.7f, 1.0f, 8.4f, 1.8f, 12.6f)
                curveToRelative(0.8f, 4.3f, 2.0f, 10.6f, 2.5f, 14.0f)
                curveToRelative(0.6f, 3.5f, 1.5f, 8.5f, 2.0f, 11.3f)
                curveToRelative(0.6f, 2.8f, 1.2f, 6.4f, 1.5f, 8.3f)
                curveToRelative(0.8f, 4.4f, 0.9f, 5.0f, 1.8f, 5.0f)
                curveToRelative(0.3f, 0.0f, 1.9f, 1.3f, 3.3f, 2.9f)
                curveToRelative(2.0f, 2.3f, 4.0f, 5.7f, 9.3f, 16.4f)
                curveToRelative(3.7f, 7.4f, 7.6f, 15.8f, 8.8f, 18.5f)
                curveToRelative(1.1f, 2.8f, 3.3f, 7.7f, 4.8f, 10.9f)
                curveToRelative(1.5f, 3.3f, 4.1f, 9.1f, 5.7f, 13.0f)
                curveToRelative(1.6f, 4.0f, 4.4f, 10.8f, 6.3f, 15.1f)
                curveToRelative(1.8f, 4.4f, 4.0f, 9.7f, 4.8f, 11.8f)
                curveToRelative(0.8f, 2.0f, 2.3f, 5.8f, 3.5f, 8.2f)
                curveToRelative(6.1f, 12.9f, 7.6f, 20.7f, 5.8f, 29.4f)
                curveToRelative(-1.2f, 5.6f, -3.2f, 9.3f, -7.1f, 13.3f)
                curveToRelative(-3.0f, 3.0f, -4.0f, 3.7f, -7.7f, 5.1f)
                curveToRelative(-3.6f, 1.4f, -5.1f, 1.6f, -9.8f, 1.9f)
                lineToRelative(-5.5f, 0.3f)
                lineToRelative(-0.1f, 27.3f)
                curveToRelative(0.0f, 16.5f, -0.3f, 30.6f, -0.6f, 35.5f)
                curveToRelative(-0.6f, 8.9f, -2.7f, 31.3f, -3.5f, 39.0f)
                curveToRelative(-0.3f, 2.6f, -0.7f, 6.9f, -1.0f, 9.5f)
                curveToRelative(-0.3f, 2.6f, -0.9f, 8.7f, -1.5f, 13.5f)
                curveToRelative(-0.6f, 4.8f, -1.3f, 10.8f, -1.5f, 13.3f)
                curveToRelative(-0.3f, 2.5f, -1.0f, 8.4f, -1.5f, 13.2f)
                curveToRelative(-0.9f, 8.4f, -1.3f, 11.6f, -2.5f, 23.3f)
                curveToRelative(-1.1f, 11.8f, -3.7f, 25.8f, -5.2f, 28.7f)
                curveToRelative(-0.4f, 0.7f, -1.3f, 1.9f, -2.0f, 2.5f)
                curveToRelative(-1.2f, 1.1f, -1.8f, 1.3f, -5.6f, 1.3f)
                curveToRelative(-4.0f, -0.1f, -4.2f, 0.0f, -4.7f, 1.3f)
                curveToRelative(-0.4f, 1.0f, -3.3f, 11.9f, -5.8f, 21.3f)
                curveToRelative(-0.4f, 1.4f, -1.1f, 4.3f, -1.8f, 6.5f)
                curveToRelative(-0.6f, 2.2f, -4.9f, 19.2f, -9.6f, 37.8f)
                curveToRelative(-12.0f, 48.0f, -14.9f, 58.3f, -18.0f, 64.9f)
                curveToRelative(-0.8f, 1.8f, -1.4f, 3.3f, -1.3f, 3.3f)
                curveToRelative(0.1f, 0.1f, 5.8f, 0.6f, 12.6f, 1.0f)
                curveToRelative(6.9f, 0.4f, 13.5f, 1.0f, 14.8f, 1.2f)
                curveToRelative(4.7f, 0.9f, 4.9f, 0.8f, 2.5f, -1.3f)
                close()
                moveTo(155.3f, 813.7f)
                curveToRelative(15.8f, -13.3f, 20.5f, -16.4f, 23.3f, -15.2f)
                curveToRelative(1.8f, 0.8f, 4.3f, 3.3f, 4.9f, 5.2f)
                curveToRelative(1.5f, 3.8f, -0.1f, 7.3f, -5.8f, 12.8f)
                lineToRelative(-4.3f, 4.2f)
                lineToRelative(5.8f, -0.3f)
                curveToRelative(3.2f, -0.2f, 7.7f, -0.6f, 10.0f, -0.9f)
                curveToRelative(2.3f, -0.3f, 6.8f, -0.4f, 9.9f, -0.4f)
                curveToRelative(3.0f, 0.0f, 5.6f, -0.2f, 5.6f, -0.3f)
                curveToRelative(0.0f, -0.2f, -0.3f, -0.9f, -0.7f, -1.6f)
                curveToRelative(-1.0f, -1.8f, -3.6f, -9.4f, -4.8f, -13.8f)
                curveToRelative(-0.9f, -3.5f, -13.4f, -53.1f, -20.5f, -81.8f)
                curveToRelative(-2.0f, -7.8f, -4.4f, -17.3f, -5.4f, -21.0f)
                curveToRelative(-1.0f, -3.7f, -2.4f, -8.6f, -3.0f, -11.0f)
                curveToRelative(-0.6f, -2.3f, -1.2f, -4.5f, -1.3f, -4.8f)
                curveToRelative(-0.1f, -0.4f, -1.8f, -0.6f, -4.4f, -0.8f)
                curveToRelative(-4.8f, -0.2f, -6.3f, -1.0f, -7.9f, -4.1f)
                curveToRelative(-1.8f, -3.5f, -4.6f, -20.2f, -6.4f, -39.2f)
                curveToRelative(-0.9f, -10.0f, -2.2f, -20.9f, -3.8f, -34.0f)
                curveToRelative(-1.9f, -14.7f, -3.5f, -29.4f, -4.2f, -36.3f)
                curveToRelative(-3.5f, -35.8f, -4.2f, -50.2f, -4.0f, -76.2f)
                lineToRelative(0.2f, -18.5f)
                lineToRelative(-4.6f, 0.3f)
                curveToRelative(-5.1f, 0.4f, -8.3f, -0.3f, -12.9f, -2.6f)
                curveToRelative(-2.9f, -1.5f, -7.7f, -6.1f, -9.5f, -9.3f)
                curveToRelative(-3.2f, -5.3f, -4.4f, -17.4f, -2.6f, -24.3f)
                curveToRelative(0.4f, -1.8f, 4.3f, -11.1f, 8.4f, -20.8f)
                curveToRelative(4.2f, -9.7f, 9.1f, -21.4f, 10.9f, -26.1f)
                curveToRelative(7.3f, -18.0f, 13.1f, -31.1f, 20.0f, -44.5f)
                curveToRelative(6.7f, -13.1f, 7.3f, -14.2f, 11.3f, -17.4f)
                lineToRelative(2.5f, -2.0f)
                lineToRelative(0.9f, -5.1f)
                curveToRelative(0.5f, -2.8f, 1.7f, -9.7f, 2.7f, -15.4f)
                curveToRelative(1.0f, -5.7f, 2.3f, -12.9f, 2.8f, -16.0f)
                curveToRelative(2.2f, -12.1f, 3.0f, -17.1f, 3.0f, -18.0f)
                curveToRelative(0.0f, -0.7f, -0.3f, -0.9f, -1.5f, -0.9f)
                curveToRelative(-1.1f, 0.0f, -1.9f, 0.5f, -3.1f, 2.2f)
                curveToRelative(-2.5f, 3.2f, -9.6f, 11.0f, -20.2f, 22.3f)
                curveToRelative(-5.2f, 5.6f, -9.6f, 10.4f, -9.9f, 10.8f)
                curveToRelative(-1.1f, 1.9f, -4.7f, 18.0f, -5.9f, 26.8f)
                curveToRelative(-0.9f, 6.4f, -1.3f, 9.9f, -2.5f, 24.0f)
                curveToRelative(-2.2f, 25.5f, -5.6f, 32.7f, -27.2f, 57.3f)
                curveToRelative(-5.4f, 6.2f, -6.8f, 8.5f, -10.1f, 16.2f)
                curveToRelative(-1.5f, 3.7f, -3.2f, 7.4f, -3.8f, 8.3f)
                curveToRelative(-1.9f, 3.1f, -6.1f, 6.3f, -10.3f, 7.8f)
                curveToRelative(-2.5f, 0.9f, -2.8f, 1.1f, -2.8f, 2.7f)
                curveToRelative(0.0f, 0.9f, -0.6f, 5.6f, -1.2f, 10.4f)
                curveToRelative(-3.5f, 25.0f, -4.9f, 55.5f, -3.1f, 66.2f)
                curveToRelative(1.3f, 7.6f, 6.9f, 34.0f, 12.6f, 58.8f)
                curveToRelative(1.2f, 5.3f, 4.6f, 20.5f, 7.5f, 33.6f)
                curveToRelative(6.1f, 27.3f, 7.3f, 32.3f, 10.4f, 39.5f)
                curveToRelative(3.0f, 7.3f, 3.5f, 8.8f, 2.8f, 12.3f)
                curveToRelative(-0.3f, 1.6f, -0.6f, 3.3f, -0.8f, 4.0f)
                curveToRelative(-0.3f, 0.7f, 0.3f, 1.7f, 1.7f, 3.5f)
                curveToRelative(2.5f, 3.0f, 3.7f, 5.4f, 5.1f, 10.6f)
                curveToRelative(2.8f, 10.0f, 4.3f, 14.9f, 8.7f, 28.2f)
                curveToRelative(6.8f, 20.5f, 12.1f, 34.7f, 17.0f, 45.7f)
                curveToRelative(4.7f, 10.6f, 8.9f, 26.2f, 10.5f, 39.5f)
                curveToRelative(0.4f, 3.6f, 1.0f, 8.4f, 1.3f, 10.6f)
                curveToRelative(0.3f, 2.3f, 0.4f, 4.4f, 0.3f, 4.8f)
                curveToRelative(-0.4f, 1.3f, 0.9f, 0.3f, 8.4f, -6.1f)
                close()


            }
        }
            .build()
        return f1sidepods!!
    }

private var f1sidepods: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1Sidepods, contentDescription = "")
    }
}
