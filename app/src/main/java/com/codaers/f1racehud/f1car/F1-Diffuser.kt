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

val F1DIFFUSER: ImageVector
    get() {
        if (f1diffuser != null) {
            return f1diffuser!!
        }
        f1diffuser = Builder(name = "F1test", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(112.8f, 980.2f)

                moveTo(160.8f, 881.6f)
                curveToRelative(6.6f, -0.3f, 17.5f, -0.8f, 24.1f, -1.0f)
                curveToRelative(6.7f, -0.3f, 12.4f, -0.6f, 12.8f, -0.8f)
                curveToRelative(0.6f, -0.3f, 0.6f, -0.9f, -0.6f, -6.2f)
                lineToRelative(-0.4f, -1.8f)
                lineToRelative(-9.4f, -0.2f)
                curveToRelative(-10.6f, -0.2f, -42.8f, 0.7f, -54.5f, 1.5f)
                curveToRelative(-24.8f, 1.6f, -25.9f, 1.5f, -28.4f, -1.1f)
                curveToRelative(-1.3f, -1.4f, -1.4f, -1.8f, -1.4f, -5.9f)
                curveToRelative(0.0f, -4.2f, 0.1f, -4.4f, 1.9f, -7.3f)
                curveToRelative(1.1f, -1.6f, 3.2f, -4.3f, 4.8f, -6.1f)
                curveToRelative(1.5f, -1.7f, 4.1f, -5.4f, 5.6f, -8.2f)
                curveToRelative(4.7f, -8.3f, 7.3f, -10.4f, 12.2f, -9.9f)
                curveToRelative(1.6f, 0.2f, 2.3f, -0.2f, 8.1f, -4.9f)
                curveToRelative(3.5f, -2.8f, 7.3f, -5.8f, 8.6f, -6.7f)
                curveToRelative(1.3f, -0.9f, 2.2f, -1.8f, 2.0f, -2.0f)
                curveToRelative(-0.3f, -0.5f, -3.2f, -0.1f, -4.0f, 0.5f)
                curveToRelative(-0.3f, 0.3f, -8.7f, 3.7f, -18.8f, 7.7f)
                curveToRelative(-10.1f, 4.0f, -20.7f, 8.4f, -23.8f, 9.8f)
                lineToRelative(-5.5f, 2.5f)
                lineToRelative(-0.2f, 20.6f)
                lineToRelative(-0.1f, 20.6f)
                lineToRelative(27.4f, -0.3f)
                curveToRelative(15.1f, -0.2f, 32.8f, -0.6f, 39.4f, -0.8f)
                close()
                moveTo(350.0f, 861.5f)
                curveToRelative(0.0f, -14.3f, -0.2f, -21.0f, -0.5f, -21.0f)
                curveToRelative(-0.3f, 0.0f, -3.7f, -1.4f, -7.4f, -3.0f)
                curveToRelative(-8.8f, -3.8f, -18.4f, -7.7f, -25.4f, -10.1f)
                curveToRelative(-3.0f, -1.0f, -8.1f, -3.0f, -11.4f, -4.4f)
                curveToRelative(-8.3f, -3.5f, -8.9f, -3.3f, -3.5f, 1.0f)
                curveToRelative(1.9f, 1.5f, 5.8f, 4.8f, 8.6f, 7.3f)
                curveToRelative(4.8f, 4.2f, 5.3f, 4.6f, 6.2f, 3.9f)
                curveToRelative(2.0f, -1.5f, 5.4f, -0.4f, 8.1f, 2.3f)
                curveToRelative(0.9f, 0.9f, 2.8f, 3.8f, 4.3f, 6.6f)
                curveToRelative(1.4f, 2.7f, 4.0f, 6.4f, 5.6f, 8.4f)
                curveToRelative(1.6f, 1.9f, 3.8f, 4.7f, 4.8f, 6.2f)
                curveToRelative(1.8f, 2.7f, 1.9f, 2.8f, 1.9f, 7.6f)
                curveToRelative(0.0f, 4.4f, -0.1f, 4.9f, -1.2f, 6.1f)
                curveToRelative(-0.7f, 0.7f, -2.0f, 1.5f, -3.0f, 1.8f)
                curveToRelative(-1.8f, 0.4f, -9.4f, 0.2f, -25.6f, -1.0f)
                curveToRelative(-13.8f, -1.0f, -63.1f, -2.0f, -63.8f, -1.3f)
                curveToRelative(-0.2f, 0.2f, -0.6f, 2.2f, -1.1f, 4.6f)
                lineToRelative(-0.9f, 4.3f)
                lineToRelative(2.0f, 0.0f)
                curveToRelative(1.1f, 0.0f, 7.9f, 0.3f, 15.1f, 0.6f)
                curveToRelative(21.1f, 0.9f, 40.6f, 1.3f, 64.7f, 1.4f)
                lineToRelative(22.6f, 0.1f)
                lineToRelative(0.0f, -21.0f)
                close()
                moveTo(117.3f, 870.3f)
                curveToRelative(3.3f, -0.2f, 8.3f, -0.5f, 11.0f, -0.8f)
                curveToRelative(5.9f, -0.6f, 48.3f, -2.0f, 59.8f, -2.0f)
                lineToRelative(8.0f, 0.0f)
                lineToRelative(-0.3f, -1.1f)
                curveToRelative(-0.2f, -0.6f, -1.4f, -5.3f, -2.7f, -10.3f)
                curveToRelative(-1.3f, -5.0f, -2.4f, -9.3f, -2.5f, -9.4f)
                curveToRelative(-0.3f, -0.3f, -13.3f, 0.6f, -27.7f, 1.8f)
                curveToRelative(-3.3f, 0.3f, -9.9f, 0.8f, -14.8f, 1.1f)
                lineToRelative(-8.7f, 0.6f)
                lineToRelative(-5.3f, 4.2f)
                curveToRelative(-2.9f, 2.3f, -6.6f, 5.3f, -8.3f, 6.8f)
                curveToRelative(-1.7f, 1.5f, -3.8f, 3.2f, -4.7f, 3.8f)
                curveToRelative(-2.3f, 1.6f, -7.3f, 3.0f, -10.7f, 3.0f)
                curveToRelative(-1.5f, 0.0f, -3.0f, 0.2f, -3.2f, 0.5f)
                curveToRelative(-0.5f, 0.8f, 1.6f, 2.5f, 2.9f, 2.3f)
                curveToRelative(0.6f, -0.2f, 3.8f, -0.4f, 7.1f, -0.6f)
                close()
                moveTo(336.9f, 869.6f)
                curveToRelative(0.8f, -1.0f, -0.2f, -1.5f, -3.2f, -1.5f)
                curveToRelative(-3.3f, -0.1f, -7.8f, -1.5f, -10.5f, -3.3f)
                curveToRelative(-1.4f, -0.9f, -6.2f, -4.7f, -10.6f, -8.4f)
                lineToRelative(-8.1f, -6.8f)
                lineToRelative(-8.3f, -0.3f)
                curveToRelative(-4.6f, -0.2f, -11.0f, -0.6f, -14.2f, -0.9f)
                curveToRelative(-7.3f, -0.6f, -17.8f, -1.4f, -24.1f, -1.8f)
                lineToRelative(-4.8f, -0.3f)
                lineToRelative(-0.9f, 3.9f)
                curveToRelative(-0.5f, 2.2f, -1.4f, 6.0f, -1.9f, 8.4f)
                curveToRelative(-0.5f, 2.5f, -1.1f, 5.3f, -1.4f, 6.4f)
                curveToRelative(-0.3f, 1.4f, -0.3f, 2.0f, 0.1f, 2.1f)
                curveToRelative(0.3f, 0.2f, 11.9f, 0.4f, 25.9f, 0.8f)
                curveToRelative(25.1f, 0.6f, 34.7f, 0.9f, 49.5f, 2.2f)
                curveToRelative(8.7f, 0.8f, 11.4f, 0.6f, 12.4f, -0.6f)
                close()
                moveTo(114.0f, 863.7f)
                curveToRelative(1.8f, -0.4f, 3.8f, -1.2f, 4.6f, -1.6f)
                curveToRelative(1.1f, -0.7f, 20.5f, -16.8f, 31.5f, -26.0f)
                curveToRelative(2.1f, -1.8f, 5.3f, -4.4f, 7.1f, -5.9f)
                curveToRelative(7.2f, -5.9f, 21.1f, -19.4f, 22.0f, -21.1f)
                curveToRelative(1.8f, -3.3f, 0.9f, -6.9f, -1.8f, -6.9f)
                curveToRelative(-1.1f, 0.0f, -8.6f, 5.4f, -15.3f, 11.1f)
                curveToRelative(-5.7f, 4.8f, -29.6f, 24.8f, -31.9f, 26.5f)
                curveToRelative(-2.3f, 1.7f, -3.0f, 1.7f, -4.0f, -0.2f)
                curveToRelative(-1.1f, -1.9f, -1.5f, -1.9f, -3.5f, 0.2f)
                curveToRelative(-3.3f, 3.5f, -4.0f, 5.7f, -2.2f, 6.2f)
                curveToRelative(2.0f, 0.6f, 1.1f, 2.1f, -2.5f, 4.4f)
                curveToRelative(-1.9f, 1.2f, -4.1f, 3.0f, -4.8f, 4.1f)
                curveToRelative(-0.8f, 1.0f, -2.1f, 2.7f, -3.0f, 3.7f)
                curveToRelative(-2.0f, 2.3f, -3.3f, 4.8f, -3.0f, 5.7f)
                curveToRelative(0.3f, 0.9f, 2.7f, 0.9f, 6.8f, -0.1f)
                close()
                moveTo(337.8f, 864.0f)
                curveToRelative(0.4f, -0.6f, -0.9f, -2.5f, -5.1f, -7.9f)
                curveToRelative(-2.2f, -2.8f, -3.7f, -4.2f, -6.2f, -5.6f)
                curveToRelative(-1.8f, -1.0f, -3.3f, -2.2f, -3.4f, -2.7f)
                curveToRelative(-0.1f, -0.5f, 0.4f, -1.4f, 1.1f, -2.1f)
                curveToRelative(0.7f, -0.6f, 1.3f, -1.4f, 1.3f, -1.5f)
                curveToRelative(0.0f, -1.1f, -4.3f, -6.1f, -5.3f, -6.1f)
                curveToRelative(-0.3f, 0.0f, -0.9f, 0.6f, -1.4f, 1.3f)
                curveToRelative(-0.5f, 0.8f, -1.3f, 1.3f, -2.0f, 1.3f)
                curveToRelative(-1.1f, 0.0f, -2.0f, -0.6f, -5.8f, -3.8f)
                curveToRelative(-0.7f, -0.6f, -4.8f, -4.0f, -9.0f, -7.4f)
                curveToRelative(-4.3f, -3.5f, -9.8f, -8.1f, -12.3f, -10.1f)
                curveToRelative(-8.1f, -7.0f, -18.4f, -15.0f, -20.6f, -16.1f)
                curveToRelative(-1.6f, -0.9f, -2.5f, -1.0f, -3.0f, -0.6f)
                curveToRelative(-1.2f, 0.8f, -1.8f, 3.0f, -1.4f, 5.4f)
                curveToRelative(0.3f, 2.0f, 0.9f, 2.7f, 4.8f, 6.4f)
                curveToRelative(6.2f, 5.8f, 25.3f, 22.5f, 36.5f, 31.9f)
                curveToRelative(12.4f, 10.3f, 19.3f, 15.6f, 21.4f, 16.5f)
                curveToRelative(3.8f, 1.6f, 9.8f, 2.5f, 10.4f, 1.4f)
                close()
                moveTo(160.0f, 844.8f)
                curveToRelative(5.3f, -0.4f, 12.7f, -0.9f, 16.3f, -1.3f)
                curveToRelative(18.1f, -1.4f, 22.5f, -1.6f, 34.5f, -2.0f)
                curveToRelative(18.0f, -0.4f, 37.6f, 0.3f, 57.8f, 2.0f)
                curveToRelative(3.2f, 0.3f, 10.7f, 0.9f, 16.8f, 1.3f)
                curveToRelative(6.1f, 0.4f, 11.4f, 0.9f, 11.9f, 1.0f)
                curveToRelative(2.2f, 0.6f, 0.9f, -1.0f, -3.8f, -5.2f)
                curveToRelative(-12.3f, -10.8f, -15.7f, -13.7f, -16.9f, -14.4f)
                curveToRelative(-1.5f, -0.8f, -12.6f, -2.0f, -26.0f, -2.8f)
                curveToRelative(-14.3f, -0.9f, -52.8f, -0.6f, -64.5f, 0.3f)
                curveToRelative(-5.1f, 0.4f, -11.1f, 0.9f, -13.4f, 1.1f)
                lineToRelative(-4.1f, 0.3f)
                lineToRelative(-3.7f, 3.0f)
                curveToRelative(-2.0f, 1.7f, -6.3f, 5.3f, -9.4f, 8.1f)
                curveToRelative(-3.2f, 2.8f, -6.8f, 5.8f, -8.1f, 6.9f)
                curveToRelative(-3.2f, 2.5f, -3.1f, 3.0f, 0.3f, 2.7f)
                curveToRelative(1.4f, -0.2f, 7.0f, -0.6f, 12.4f, -1.0f)
                close()
            }


        }
            .build()
        return f1diffuser!!
    }

private var f1diffuser: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1DIFFUSER, contentDescription = "")
    }
}