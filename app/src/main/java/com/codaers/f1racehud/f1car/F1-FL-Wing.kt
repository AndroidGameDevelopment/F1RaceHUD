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

val F1FLWing: ImageVector
    get() {
        if (f1flwing != null) {
            return f1flwing!!
        }
        f1flwing = Builder(name = "F1original", defaultWidth = 442.0.dp, defaultHeight =
            990.0.dp, viewportWidth = 442.0f, viewportHeight = 990.0f).apply {


            path(fill = SolidColor(Color.Green), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                //moveTo(402.8f, 980.2f)

                moveTo(093.7f, 109.8f)
                curveToRelative(-0.1f, -7.9f, -1.0f, -17.5f, -4.0f, -38.0f)
                curveToRelative(-0.7f, -4.9f, -1.7f, -12.6f, -2.3f, -17.0f)
                curveToRelative(-0.5f, -4.4f, -1.1f, -9.4f, -1.4f, -11.3f)
                curveToRelative(-0.3f, -1.8f, -0.8f, -6.2f, -1.3f, -9.8f)
                curveToRelative(-0.4f, -3.5f, -0.9f, -7.4f, -1.1f, -8.6f)
                curveToRelative(-0.3f, -2.7f, -0.8f, -2.7f, -6.9f, -0.7f)
                lineToRelative(-4.5f, 1.5f)
                lineToRelative(-0.2f, 44.7f)
                curveToRelative(-0.1f, 24.5f, 0.0f, 45.0f, 0.2f, 45.3f)
                curveToRelative(0.2f, 0.5f, 2.7f, 0.6f, 10.9f, 0.5f)
                lineToRelative(10.6f, -0.2f)
                lineToRelative(-0.1f, -6.5f)
                close()
                moveTo(102.5f, 66.3f)
                curveToRelative(0.0f, -27.6f, -0.2f, -50.3f, -0.4f, -50.3f)
                curveToRelative(-0.2f, 0.0f, -2.4f, 0.4f, -4.8f, 0.9f)
                curveToRelative(-2.5f, 0.6f, -5.7f, 1.1f, -7.1f, 1.3f)
                curveToRelative(-1.5f, 0.2f, -2.8f, 0.6f, -3.1f, 0.9f)
                curveToRelative(-0.3f, 0.4f, -0.3f, 2.0f, 0.2f, 5.1f)
                curveToRelative(0.7f, 4.3f, 1.5f, 9.9f, 4.8f, 35.5f)
                curveToRelative(0.9f, 6.3f, 1.7f, 12.9f, 2.0f, 14.8f)
                curveToRelative(1.8f, 11.6f, 3.5f, 28.8f, 3.6f, 36.2f)
                lineToRelative(0.1f, 5.8f)
                lineToRelative(2.4f, 0.0f)
                lineToRelative(2.4f, 0.0f)
                lineToRelative(0.0f, -50.3f)
                close()
                moveTo(199.9f, 48.8f)
                curveToRelative(0.3f, -0.3f, 0.9f, -4.9f, 1.3f, -10.3f)
                curveToRelative(0.4f, -5.3f, 1.1f, -12.6f, 1.4f, -16.1f)
                curveToRelative(0.3f, -3.5f, 0.3f, -6.6f, 0.2f, -6.8f)
                curveToRelative(-0.2f, -0.2f, -22.0f, -0.3f, -48.4f, -0.4f)
                lineToRelative(-48.1f, -0.1f)
                lineToRelative(0.2f, 17.3f)
                lineToRelative(0.2f, 17.2f)
                lineToRelative(46.5f, -0.2f)
                curveToRelative(30.1f, -0.2f, 46.6f, -0.4f, 46.9f, -0.7f)
                close()
                moveTo(196.5f, 89.1f)
                curveToRelative(0.3f, -0.1f, 0.3f, -0.3f, 1.9f, -19.8f)
                curveToRelative(0.6f, -6.8f, 1.1f, -13.4f, 1.3f, -14.4f)
                lineToRelative(0.3f, -2.0f)
                lineToRelative(-13.6f, 0.3f)
                curveToRelative(-7.5f, 0.2f, -28.5f, 0.3f, -46.8f, 0.3f)
                lineToRelative(-33.2f, 0.0f)
                lineToRelative(0.0f, 17.8f)
                lineToRelative(0.0f, 17.8f)
                lineToRelative(37.7f, 0.1f)
                curveToRelative(31.1f, 0.2f, 50.8f, 0.1f, 52.4f, -0.1f)
                close()


            }
        }
            .build()
        return f1flwing!!
    }

private var f1flwing: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = F1FLWing, contentDescription = "")
    }
}
