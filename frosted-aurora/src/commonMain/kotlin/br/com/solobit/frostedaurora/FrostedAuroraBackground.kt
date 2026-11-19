/*
 * Frosted Aurora - Dynamic Frosted Gradient Background SDK for Compose Multiplatform
 * Author: Willian Santos - solobit.com.br
 * License: Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 * https://creativecommons.org/licenses/by-nc/4.0/
 */

package br.com.solobit.frostedaurora

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * Container with a dynamic, frosted radial-gradient background.
 * Suitable for full-screen backgrounds or wrapping Cards and surfaces.
 */
@Composable
fun FrostedAuroraBox(
    modifier: Modifier = Modifier,
    palette: FrostedAuroraPalette = FrostedAuroraDefaults.DefaultPalette,
    speed: Float = 1f,
    isDark: Boolean = isSystemInDarkTheme(),
    frostAlpha: Float = if (isDark) 0.68f else 0.62f,
    animated: Boolean = speed > 0f,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val safeSpeed = speed.coerceAtLeast(0.05f)
    val transition = rememberInfiniteTransition(label = "frosted_aurora")

    val animatedBlobs = palette.blobs.mapIndexed { index, blob ->
        if (animated) {
            val phaseDuration = max(1_000, (blob.phaseDurationMs / safeSpeed).roundToInt())
            val colorDuration = max(1_000, (blob.colorDurationMs / safeSpeed).roundToInt())

            val phase by transition.animateFloat(
                initialValue = blob.phaseOffsetRad,
                targetValue = blob.phaseOffsetRad + (2f * PI).toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = phaseDuration, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ),
                label = "blob_phase_$index"
            )
            val color by transition.animateColor(
                initialValue = blob.startColor,
                targetValue = blob.endColor,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = colorDuration, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "blob_color_$index"
            )
            Triple(blob, phase, color)
        } else {
            Triple(blob, blob.phaseOffsetRad, blob.startColor)
        }
    }

    val baseColor = if (isDark) palette.darkBaseColor else palette.lightBaseColor
    val frostColor = if (isDark) palette.darkFrostColor else palette.lightFrostColor

    Box(
        modifier = modifier
            .clipToBounds()
            .background(baseColor)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            animatedBlobs.forEach { (blob, phase, color) ->
                val alpha = if (isDark) blob.darkAlpha else blob.lightAlpha
                val center = Offset(
                    x = size.width * (blob.baseCenter.x + blob.driftAmplitude.x * cos(phase)),
                    y = size.height * (blob.baseCenter.y + blob.driftAmplitude.y * sin(phase))
                )
                val radius = size.width * (blob.radiusFraction + 0.06f * sin(phase))
                val scaleX = blob.baseScaleX + 0.12f * cos(phase)
                val scaleY = blob.baseScaleY + 0.12f * sin(phase)

                drawSoftEllipse(
                    color = color.copy(alpha = alpha),
                    center = center,
                    radius = radius,
                    scaleX = scaleX,
                    scaleY = scaleY
                )
            }
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(frostColor.copy(alpha = frostAlpha.coerceIn(0f, 1f)))
        )

        content()
    }
}

/**
 * Convenience leaf composable for full-bleed backgrounds without nested content.
 */
@Composable
fun FrostedAuroraBackground(
    modifier: Modifier = Modifier.fillMaxSize(),
    palette: FrostedAuroraPalette = FrostedAuroraDefaults.DefaultPalette,
    speed: Float = 1f,
    isDark: Boolean = isSystemInDarkTheme(),
    frostAlpha: Float = if (isDark) 0.68f else 0.62f,
    animated: Boolean = speed > 0f
) {
    FrostedAuroraBox(
        modifier = modifier,
        palette = palette,
        speed = speed,
        isDark = isDark,
        frostAlpha = frostAlpha,
        animated = animated
    )
}

private fun DrawScope.drawSoftEllipse(
    color: Color,
    center: Offset,
    radius: Float,
    scaleX: Float,
    scaleY: Float
) {
    withTransform({
        scale(scaleX = scaleX, scaleY = scaleY, pivot = center)
    }) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(color, Color.Transparent),
                center = center,
                radius = radius
            ),
            radius = radius,
            center = center
        )
    }
}
