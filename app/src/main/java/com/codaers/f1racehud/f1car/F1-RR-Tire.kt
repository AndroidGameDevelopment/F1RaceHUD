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

val F1RRTire: ImageVector
    get() {
        if (f1rrtire != null) {
            return f1rrtire!!
        }
        f1rrtire = Builder(name = "F1original", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {


            path(fill = SolidColor(Color.Green), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(402.8f, 980.2f)

                moveTo(425.0f, 944.0f)
                curveToRelative(3.5f, -1.6f, 6.8f, -4.9f, 8.8f, -8.8f)
                lineToRelative(1.5f, -3.0f)
                lineToRelative(0.0f, -60.0f)
                lineToRelative(0.0f, -60.0f)
                lineToRelative(-1.2f, -2.4f)
                curveToRelative(-1.5f, -3.1f, -5.0f, -6.9f, -7.7f, -8.5f)
                curveToRelative(-5.1f, -2.9f, -4.9f, -2.9f, -33.2f, -2.8f)
                lineToRelative(-26.0f, 0.2f)
                lineToRelative(-2.8f, 1.4f)
                curveToRelative(-3.8f, 1.9f, -6.8f, 5.0f, -8.7f, 8.9f)
                lineToRelative(-1.5f, 3.3f)
                lineToRelative(-0.2f, 58.3f)
                curveToRelative(-0.1f, 43.0f, 0.0f, 59.0f, 0.4f, 61.0f)
                curveToRelative(1.4f, 6.5f, 6.7f, 11.9f, 13.3f, 13.6f)
                curveToRelative(0.9f, 0.2f, 13.4f, 0.3f, 28.0f, 0.3f)
                lineToRelative(26.5f, -0.1f)
                lineToRelative(2.8f, -1.3f)
                close()
            }
        }
            .build()
        return f1rrtire!!
    }

private var f1rrtire: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1RRTire, contentDescription = "")
    }
}