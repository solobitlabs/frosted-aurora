/*
 * Frosted Aurora - Dynamic Frosted Gradient Background SDK for Compose Multiplatform
 * Author: Willian Santos - solobit.com.br
 * License: Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)
 * https://creativecommons.org/licenses/by-nc/4.0/
 */

package br.com.solobit.frostedaurora

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

/**
 * Configuration for a single animated radial gradient ellipse.
 */
@Immutable
data class FrostedBlobConfig(
    val startColor: Color,
    val endColor: Color,
    val lightAlpha: Float,
    val darkAlpha: Float = (lightAlpha * 0.75f).coerceIn(0f, 1f),
    val baseCenter: Offset,
    val driftAmplitude: Offset = Offset(0.16f, 0.12f),
    val radiusFraction: Float = 0.75f,
    val phaseDurationMs: Int = 14_000,
    val colorDurationMs: Int = 11_000,
    val phaseOffsetRad: Float = 0f,
    val baseScaleX: Float = 1.20f,
    val baseScaleY: Float = 0.85f
)

/**
 * Color palette and surface configuration for [FrostedAuroraBox] and [FrostedAuroraBackground].
 */
@Immutable
data class FrostedAuroraPalette(
    val lightBaseColor: Color = Color.White,
    val darkBaseColor: Color = Color(0xFF0D1117),
    val lightFrostColor: Color = Color.White,
    val darkFrostColor: Color = Color(0xFF0D1117),
    val blobs: List<FrostedBlobConfig>
)

/**
 * Built-in presets for Frosted Aurora.
 */
object FrostedAuroraDefaults {
    val DefaultPalette = FrostedAuroraPalette(
        blobs = listOf(
            FrostedBlobConfig(
                startColor = Color(0xFF00C853),
                endColor = Color(0xFF00B8D4),
                lightAlpha = 0.65f,
                darkAlpha = 0.50f,
                baseCenter = Offset(0.25f, 0.22f),
                driftAmplitude = Offset(0.18f, 0.12f),
                radiusFraction = 0.75f,
                phaseDurationMs = 14_000,
                colorDurationMs = 10_000,
                phaseOffsetRad = 0f,
                baseScaleX = 1.25f,
                baseScaleY = 0.85f
            ),
            FrostedBlobConfig(
                startColor = Color(0xFFFF5722),
                endColor = Color(0xFFFF4081),
                lightAlpha = 0.55f,
                darkAlpha = 0.42f,
                baseCenter = Offset(0.78f, 0.48f),
                driftAmplitude = Offset(0.16f, 0.15f),
                radiusFraction = 0.80f,
                phaseDurationMs = 18_000,
                colorDurationMs = 13_000,
                phaseOffsetRad = 1.2f,
                baseScaleX = 0.90f,
                baseScaleY = 1.25f
            ),
            FrostedBlobConfig(
                startColor = Color(0xFF25669B),
                endColor = Color(0xFF651FFF),
                lightAlpha = 0.50f,
                darkAlpha = 0.45f,
                baseCenter = Offset(0.28f, 0.80f),
                driftAmplitude = Offset(0.22f, 0.12f),
                radiusFraction = 0.72f,
                phaseDurationMs = 22_000,
                colorDurationMs = 16_000,
                phaseOffsetRad = 2.4f,
                baseScaleX = 1.30f,
                baseScaleY = 0.80f
            )
        )
    )

    val OceanPalette = FrostedAuroraPalette(
        blobs = listOf(
            FrostedBlobConfig(
                startColor = Color(0xFF00E5FF),
                endColor = Color(0xFF1DE9B6),
                lightAlpha = 0.60f,
                baseCenter = Offset(0.22f, 0.25f),
                phaseDurationMs = 15_000,
                colorDurationMs = 11_000
            ),
            FrostedBlobConfig(
                startColor = Color(0xFF2979FF),
                endColor = Color(0xFF00B0FF),
                lightAlpha = 0.55f,
                baseCenter = Offset(0.80f, 0.55f),
                phaseDurationMs = 19_000,
                colorDurationMs = 14_000,
                phaseOffsetRad = 1.5f
            ),
            FrostedBlobConfig(
                startColor = Color(0xFF3D5AFE),
                endColor = Color(0xFF651FFF),
                lightAlpha = 0.48f,
                baseCenter = Offset(0.30f, 0.82f),
                phaseDurationMs = 23_000,
                colorDurationMs = 17_000,
                phaseOffsetRad = 3.0f
            )
        )
    )
}
