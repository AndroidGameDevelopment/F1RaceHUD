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

val F1FRTire: ImageVector
    get() {
        if (f1frtire != null) {
            return f1frtire!!
        }
        f1frtire = Builder(name = "F1original", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {


            path(fill = SolidColor(Color.Green), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(402.8f, 980.2f)

                moveTo(410.1f, 270.5f)
                curveToRelative(1.5f, -0.3f, 3.3f, -0.9f, 4.2f, -1.3f)
                curveToRelative(2.7f, -1.4f, 5.7f, -4.7f, 6.8f, -7.6f)
                curveToRelative(1.0f, -2.6f, 1.0f, -4.0f, 1.0f, -59.1f)
                curveToRelative(0.0f, -55.8f, 0.0f, -56.5f, -1.0f, -58.8f)
                curveToRelative(-1.3f, -3.0f, -4.0f, -5.8f, -7.2f, -7.6f)
                lineToRelative(-2.5f, -1.4f)
                lineToRelative(-25.8f, 0.0f)
                lineToRelative(-25.8f, 0.0f)
                lineToRelative(-2.5f, 1.4f)
                curveToRelative(-3.0f, 1.5f, -4.7f, 3.3f, -6.3f, 6.4f)
                lineToRelative(-1.2f, 2.3f)
                lineToRelative(-0.2f, 56.9f)
                curveToRelative(-0.1f, 38.4f, 0.1f, 57.5f, 0.4f, 58.8f)
                curveToRelative(1.1f, 4.2f, 5.7f, 8.4f, 10.3f, 9.8f)
                curveToRelative(3.2f, 0.9f, 45.8f, 1.0f, 49.8f, 0.2f)
                close()


            }


        }
            .build()
        return f1frtire!!
    }

private var f1frtire: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1FRTire, contentDescription = "")
    }
}