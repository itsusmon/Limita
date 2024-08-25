/*
 * Copyright (C) 2024, Usmonjon Abdurakhmanov and the Limită project contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package app.limita.feature.onboarding.impl.screen.welcome.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

// The following code is taken from a medium article written by Kappdev (https://medium.com/@kappdev)
// on "How to Create an Animated Theme Switcher in Jetpack Compose" (https://medium.com/@kappdev/how-to-create-an-animated-theme-switcher-in-jetpack-compose-32c9457ee6c3)

@Composable
internal fun ThemeSwitcher(
    darkMode: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    animationSpec: AnimationSpec<Float> = tween(400),
) {
    val progress by animateFloatAsState(
        targetValue = if (darkMode) 1f else 0f,
        animationSpec = animationSpec,
        label = "theme-switcher-progress",
    )
    Canvas(
        modifier = modifier
            .size(24.dp)
            .aspectRatio(1f)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
    ) {
        val width = size.width
        val height = size.height
        val baseRadius = width * 0.25f
        val extraRadius = width * 0.2f * progress
        val radius = baseRadius + extraRadius

        // Rotate canvas based on the progress
        rotate(180f * (1 - progress)) {
            // Calculate progress for drawing rays
            val raysProgress = if (progress < 0.5f) (progress / 0.85f) else 0f

            // Draw rays for the sun shape
            drawRays(
                color = color,
                alpha = if (progress < 0.5f) 1f else 0f,
                radius = (radius * 1.5f) * (1f - raysProgress),
                rayWidth = radius * 0.3f,
                rayLength = radius * 0.2f,
            )

            // Draw the transition between moon and sun shape
            drawMoonToSun(radius, progress, color)
        }

        // Calculate progress for drawing stars
        val starProgress = if (progress > 0.8f) ((progress - 0.8f) / 0.2f) else 0f

        // Draw stars for the moon
        drawStar(
            color = color,
            centerOffset = Offset(width * 0.4f, height * 0.4f),
            radius = (height * 0.05f) * starProgress,
            alpha = starProgress,
        )
        drawStar(
            color = color,
            centerOffset = Offset(width * 0.2f, height * 0.2f),
            radius = (height * 0.1f) * starProgress,
            alpha = starProgress,
        )
    }
}

private fun DrawScope.drawMoonToSun(radius: Float, progress: Float, color: Color) {
    // Create the main circle
    val mainCircle = Path().apply {
        addOval(Rect(center, radius))
    }

    // Calculate the initial position of the subtracting circle
    val initialOffset = center - Offset(radius * 2.3f, radius * 2.3f)

    // Calculate the offset for the subtracting circle based on the progress
    val offset = (radius * 1.8f) * progress

    // Create the subtracting circle
    val subtractCircle = Path().apply {
        addOval(Rect(initialOffset + Offset(offset, offset), radius))
    }

    // Create the final path by subtracting the subtracting circle from the main circle
    val moonToSunPath = Path().apply {
        op(mainCircle, subtractCircle, PathOperation.Difference)
    }

    // Draw the resulting path with the specified color
    drawPath(moonToSunPath, color)
}

private fun DrawScope.drawRays(
    color: Color,
    radius: Float,
    rayWidth: Float,
    rayLength: Float,
    alpha: Float = 1f,
    rayCount: Int = 8,
) {
    // Loop to draw each ray
    for (i in 0 until rayCount) {
        // Calculate the angle for the current ray
        val angle = (2 * Math.PI * i / rayCount).toFloat()

        // Calculate the starting position of the ray
        val startX = center.x + radius * cos(angle)
        val startY = center.y + radius * sin(angle)

        // Calculate the ending position of the ray
        val endX = center.x + (radius + rayLength) * cos(angle)
        val endY = center.y + (radius + rayLength) * sin(angle)

        // Draw the ray from the starting to the ending position
        drawLine(
            color = color,
            alpha = alpha,
            start = Offset(startX, startY),
            end = Offset(endX, endY),
            cap = StrokeCap.Round,
            strokeWidth = rayWidth,
        )
    }
}

private fun DrawScope.drawStar(
    color: Color,
    centerOffset: Offset,
    radius: Float,
    alpha: Float = 1f,
) {
    val leverage = radius * 0.1f

    val starPath = Path().apply {
        // Move to the leftmost point of the star
        moveTo(centerOffset.x - radius, centerOffset.y)

        // Draw the upper left curve of the star
        quadraticTo(
            x1 = centerOffset.x - leverage,
            y1 = centerOffset.y - leverage,
            x2 = centerOffset.x,
            y2 = centerOffset.y - radius,
        )

        // Draw the upper right curve of the star
        quadraticTo(
            x1 = centerOffset.x + leverage,
            y1 = centerOffset.y - leverage,
            x2 = centerOffset.x + radius,
            y2 = centerOffset.y,
        )

        // Draw the lower right curve of the star
        quadraticTo(
            x1 = centerOffset.x + leverage,
            y1 = centerOffset.y + leverage,
            x2 = centerOffset.x,
            y2 = centerOffset.y + radius,
        )

        // Draw the lower left curve of the star
        quadraticTo(
            x1 = centerOffset.x - leverage,
            y1 = centerOffset.y + leverage,
            x2 = centerOffset.x - radius,
            y2 = centerOffset.y,
        )
    }

    // Draw the star path
    drawPath(starPath, color, alpha)
}
