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

val F1FLTire: ImageVector
    get() {
        if (f1fltire != null) {
            return f1fltire!!
        }
        f1fltire = Builder(name = "F1original", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {


            path(fill = SolidColor(Color.Green), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(402.8f, 980.2f)

                moveTo(085.2f, 269.8f)
                curveToRelative(3.3f, -1.4f, 6.4f, -4.3f, 8.0f, -7.3f)
                lineToRelative(1.3f, -2.6f)
                lineToRelative(0.0f, -58.0f)
                curveToRelative(0.0f, -53.3f, -0.1f, -58.1f, -0.9f, -59.8f)
                curveToRelative(-1.1f, -2.5f, -3.8f, -4.9f, -6.6f, -6.3f)
                curveToRelative(-2.3f, -1.1f, -2.5f, -1.1f, -28.4f, -1.1f)
                lineToRelative(-26.0f, 0.0f)
                lineToRelative(-2.1f, 1.1f)
                curveToRelative(-2.7f, 1.4f, -5.3f, 4.1f, -6.8f, 6.8f)
                lineToRelative(-1.1f, 2.1f)
                lineToRelative(0.0f, 58.0f)
                lineToRelative(0.0f, 58.0f)
                lineToRelative(1.4f, 2.3f)
                curveToRelative(2.3f, 4.1f, 6.1f, 6.7f, 10.4f, 7.4f)
                curveToRelative(1.3f, 0.3f, 12.6f, 0.3f, 25.3f, 0.3f)
                curveToRelative(21.6f, -0.1f, 23.1f, -0.2f, 25.5f, -1.0f)
                close()


            }
        }
            .build()
        return f1fltire!!
    }

private var f1fltire: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1FLTire, contentDescription = "")
    }
}
