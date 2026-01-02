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

val F1RWing: ImageVector
    get() {
        if (f1rwing != null) {
            return f1rwing!!
        }
        f1rwing = Builder(name = "F1original", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {


            path(fill = SolidColor(Color.Green), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(402.8f, 980.2f)

                moveTo(332.5f, 934.4f)
                curveToRelative(0.2f, -21.5f, 0.2f, -39.2f, 0.1f, -39.3f)
                curveToRelative(-0.1f, -0.1f, -0.6f, 0.1f, -1.0f, 0.3f)
                curveToRelative(-0.8f, 0.5f, -0.9f, 3.6f, -1.1f, 40.3f)
                curveToRelative(-0.3f, 39.8f, -0.3f, 39.8f, 0.8f, 38.8f)
                curveToRelative(1.0f, -0.9f, 1.0f, -1.8f, 1.3f, -40.1f)
                close()
                moveTo(114.0f, 935.3f)
                curveToRelative(0.0f, -35.0f, -0.1f, -39.3f, -0.8f, -39.6f)
                curveToRelative(-0.4f, -0.2f, -0.9f, -0.2f, -1.0f, -0.1f)
                curveToRelative(-0.2f, 0.2f, -0.3f, 17.5f, -0.3f, 38.6f)
                curveToRelative(-0.1f, 35.8f, 0.1f, 38.5f, 0.9f, 39.3f)
                curveToRelative(0.4f, 0.5f, 0.9f, 0.9f, 1.0f, 0.9f)
                curveToRelative(0.1f, 0.0f, 0.2f, -17.6f, 0.2f, -39.3f)
                close()
                moveTo(219.9f, 958.2f)
                curveToRelative(0.2f, -0.5f, 0.3f, -11.9f, 0.3f, -25.4f)
                lineToRelative(0.0f, -24.5f)
                lineToRelative(-51.1f, -0.2f)
                lineToRelative(-51.2f, -0.1f)
                lineToRelative(0.0f, 25.1f)
                curveToRelative(0.0f, 13.9f, 0.2f, 25.4f, 0.3f, 25.5f)
                curveToRelative(0.2f, 0.2f, 23.0f, 0.3f, 50.8f, 0.3f)
                curveToRelative(47.5f, 0.0f, 50.5f, -0.1f, 50.8f, -0.9f)
                close()
                moveTo(326.4f, 933.4f)
                lineToRelative(0.1f, -25.4f)
                lineToRelative(-51.1f, 0.1f)
                lineToRelative(-51.2f, 0.2f)
                lineToRelative(-0.2f, 24.6f)
                curveToRelative(-0.1f, 22.1f, -0.1f, 24.6f, 0.7f, 25.4f)
                curveToRelative(0.8f, 0.7f, 5.3f, 0.8f, 51.2f, 0.6f)
                lineToRelative(50.3f, -0.2f)
                lineToRelative(0.2f, -25.4f)
                close()
                moveTo(237.5f, 897.3f)
                curveToRelative(2.9f, -12.6f, 4.1f, -17.4f, 6.1f, -25.7f)
                curveToRelative(2.7f, -11.2f, 4.3f, -19.3f, 4.6f, -22.5f)
                lineToRelative(0.2f, -2.8f)
                lineToRelative(-6.3f, -0.3f)
                curveToRelative(-15.9f, -0.9f, -46.3f, -0.3f, -46.8f, 0.9f)
                curveToRelative(-0.2f, 0.3f, 0.8f, 4.9f, 2.0f, 10.3f)
                curveToRelative(3.0f, 13.1f, 9.2f, 40.5f, 9.9f, 44.0f)
                curveToRelative(0.3f, 1.5f, 0.6f, 2.8f, 0.6f, 3.0f)
                curveToRelative(0.1f, 0.2f, 6.4f, 0.3f, 14.1f, 0.3f)
                lineToRelative(13.9f, 0.0f)
                lineToRelative(1.6f, -7.0f)
                close()


            }
        }
            .build()
        return f1rwing!!
    }

private var f1rwing: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1RWing, contentDescription = "")
    }
}
