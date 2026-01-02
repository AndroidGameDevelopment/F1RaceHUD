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

val F1FRWing: ImageVector
    get() {
        if (f1frwing != null) {
            return f1frwing!!
        }
        f1frwing = Builder(name = "F1original", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {


            path(fill = SolidColor(Color.Green), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(402.8f, 980.2f)

                moveTo(372.9f, 70.8f)
                curveToRelative(0.1f, -30.3f, -0.1f, -45.7f, -0.4f, -46.0f)
                curveToRelative(-0.3f, -0.3f, -1.9f, -0.9f, -3.7f, -1.2f)
                curveToRelative(-1.7f, -0.3f, -4.1f, -0.9f, -5.3f, -1.1f)
                curveToRelative(-1.2f, -0.3f, -2.3f, -0.4f, -2.4f, -0.3f)
                curveToRelative(-0.2f, 0.1f, -0.8f, 4.6f, -1.4f, 9.9f)
                curveToRelative(-1.6f, 13.3f, -4.1f, 32.0f, -4.9f, 38.7f)
                curveToRelative(-0.4f, 3.0f, -1.3f, 9.1f, -1.8f, 13.5f)
                curveToRelative(-0.6f, 4.4f, -1.3f, 9.4f, -1.5f, 11.3f)
                curveToRelative(-0.9f, 5.8f, -1.2f, 19.4f, -0.4f, 20.3f)
                curveToRelative(0.6f, 0.6f, 2.2f, 0.8f, 11.2f, 0.6f)
                lineToRelative(10.6f, -0.2f)
                lineToRelative(0.2f, -45.5f)
                close()
                moveTo(346.6f, 111.3f)
                curveToRelative(-0.2f, -6.7f, 1.1f, -19.5f, 4.3f, -42.0f)
                curveToRelative(0.8f, -5.8f, 1.9f, -13.4f, 2.3f, -17.0f)
                curveToRelative(1.3f, -10.6f, 2.8f, -21.7f, 3.5f, -26.5f)
                curveToRelative(0.4f, -2.5f, 0.8f, -4.9f, 0.8f, -5.4f)
                curveToRelative(0.0f, -1.7f, -9.5f, -5.4f, -13.8f, -5.4f)
                lineToRelative(-2.0f, 0.0f)
                lineToRelative(0.2f, 50.1f)
                curveToRelative(0.1f, 27.6f, 0.3f, 50.5f, 0.3f, 50.8f)
                curveToRelative(0.2f, 0.4f, 0.9f, 0.6f, 2.3f, 0.5f)
                lineToRelative(2.1f, -0.2f)
                lineToRelative(-0.1f, -5.0f)
                close()
                moveTo(338.0f, 32.4f)
                lineToRelative(0.0f, -17.1f)
                lineToRelative(-47.8f, 0.1f)
                lineToRelative(-47.8f, 0.0f)
                lineToRelative(0.3f, 4.0f)
                curveToRelative(0.5f, 6.9f, 1.4f, 17.3f, 2.0f, 23.5f)
                lineToRelative(0.6f, 6.0f)
                lineToRelative(4.5f, 0.3f)
                curveToRelative(2.5f, 0.2f, 23.4f, 0.3f, 46.4f, 0.3f)
                lineToRelative(41.8f, 0.1f)
                lineToRelative(0.0f, -17.1f)
                close()
                moveTo(299.7f, 89.0f)
                lineToRelative(38.3f, 0.0f)
                lineToRelative(0.0f, -17.8f)
                lineToRelative(0.0f, -17.8f)
                lineToRelative(-39.3f, 0.0f)
                curveToRelative(-21.7f, 0.0f, -42.7f, -0.2f, -46.6f, -0.3f)
                curveToRelative(-6.8f, -0.3f, -7.2f, -0.3f, -6.8f, 0.6f)
                curveToRelative(0.3f, 0.9f, 1.4f, 11.6f, 2.3f, 23.0f)
                curveToRelative(0.9f, 11.3f, 1.0f, 12.1f, 2.0f, 12.4f)
                curveToRelative(0.4f, 0.1f, 3.4f, 0.2f, 6.5f, 0.1f)
                curveToRelative(3.1f, -0.1f, 22.9f, -0.2f, 43.8f, -0.2f)
                close()


            }
        }
            .build()
        return f1frwing!!
    }

private var f1frwing: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1FRWing, contentDescription = "")
    }
}
