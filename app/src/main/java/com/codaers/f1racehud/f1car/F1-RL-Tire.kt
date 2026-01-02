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

val F1RLTire: ImageVector
    get() {
        if (f1rltire != null) {
            return f1rltire!!
        }
        f1rltire = Builder(name = "F1RLTire", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {


            path(fill = SolidColor(Color.Green), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(402.8f, 980.2f)

                moveTo(079.9f, 943.8f)
                curveToRelative(3.9f, -1.8f, 7.6f, -5.7f, 9.1f, -9.5f)
                curveToRelative(1.0f, -2.7f, 1.0f, -3.8f, 1.1f, -61.7f)
                curveToRelative(0.1f, -65.3f, 0.3f, -61.1f, -3.3f, -66.4f)
                curveToRelative(-2.0f, -3.1f, -5.2f, -5.4f, -9.1f, -6.8f)
                curveToRelative(-2.5f, -0.9f, -4.1f, -1.0f, -29.3f, -0.9f)
                lineToRelative(-26.6f, 0.2f)
                lineToRelative(-2.8f, 1.4f)
                curveToRelative(-4.8f, 2.5f, -8.3f, 6.6f, -9.5f, 11.4f)
                curveToRelative(-0.9f, 3.1f, -0.8f, 118.1f, 0.1f, 121.2f)
                curveToRelative(1.5f, 5.7f, 7.4f, 11.1f, 13.7f, 12.5f)
                curveToRelative(0.6f, 0.2f, 12.8f, 0.2f, 27.3f, 0.2f)
                lineToRelative(26.3f, -0.1f)
                lineToRelative(3.2f, -1.5f)
                close()
            }
        }
            .build()
        return f1rltire!!
    }

private var f1rltire: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1RLTire, contentDescription = "")
    }
}