package org.dev.uipolygon.core.common.icons

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object KotlinIcon {
    val Logo: ImageVector
        get() = ImageVector.Builder(
            name = "KotlinLogo",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF7F52FF),
                        Color(0xFFC811E2),
                        Color(0xFFE54857)
                    ),
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(24f, 24f)
                )
            ) {
                moveTo(24f, 24f)
                lineTo(0f, 24f)
                lineTo(0f, 0f)
                lineTo(12f, 0f)
                lineTo(24f, 12f)
                close()
            }
        }.build()
}