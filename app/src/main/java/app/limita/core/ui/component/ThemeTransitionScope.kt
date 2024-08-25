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

package app.limita.core.ui.component

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.PixelCopy.OnPixelCopyFinishedListener
import android.view.View
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import logcat.LogPriority
import logcat.logcat
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * A composable function that provides a [ThemeTransitionScope] to its content and initiates
 * the theme transition animation.
 *
 * This function captures a screenshot of the current theme, toggles the theme, and then reveals
 * the new theme using a ripple cutout animation applied to the screenshot.
 *
 * @param toggleTheme A function to toggle the app's theme (e.g., light to dark mode).
 * @param modifier The modifier to be applied to the layout.
 * @param animationSpec The animation specification for the ripple cutout effect.
 * @param coroutineScope The coroutine scope used for animations and capturing.
 * @param content The composable content to be displayed within the `ThemeTransition` scope.
 */
@Composable
fun ThemeTransitionScope(
    toggleTheme: () -> Unit,
    modifier: Modifier = Modifier,
    animationSpec: AnimationSpec<Float> = tween(1200),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable ThemeTransitionScope.() -> Unit,
) {
    var screenBounds by remember { mutableStateOf(Rect.Zero) }
    Box(
        modifier = modifier.onGloballyPositioned { screenBounds = it.boundsInWindow() },
    ) {
        val view: View = LocalView.current
        var screenshot: Bitmap? by remember { mutableStateOf(null) }
        var switchOffset by remember { mutableStateOf(Offset.Zero) }
        val radius = remember { Animatable(0f) }
        val targetRadius = calculateTargetRadius(switchOffset)

        val themeTransitionScope = remember(coroutineScope, radius, toggleTheme) {
            object : ThemeTransitionScope {
                override fun startThemeTransition(offset: Offset) {
                    if (radius.isRunning) return

                    coroutineScope.launch {
                        screenshot = view.screenshot(screenBounds)
                        switchOffset = offset

                        launch {
                            try {
                                radius.animateTo(targetRadius, animationSpec)
                            } finally {
                                screenshot?.recycle()
                                screenshot = null
                                radius.snapTo(0f)
                            }
                        }

                        toggleTheme()
                    }
                }
            }
        }

        content(themeTransitionScope)

        screenshot?.asImageBitmap()?.let { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RippleCutoutShape(switchOffset, radius.value)),
            )
        }
    }
}

/**
 * An interface representing a scope for composables that can trigger a theme transition animation.
 */
@Stable
interface ThemeTransitionScope {

    /**
     * Starts the theme transition animation with a ripple effect originating from the given offset.
     *
     * @param offset The offset from which the ripple animation should originate.
     */
    fun startThemeTransition(offset: Offset)
}

/**
 * A shape that creates a ripple cutout effect used in the theme transition animation.
 *
 * This shape defines a circular cutout that expands to reveal the new theme underneath the screenshot.
 *
 * @param offset The offset of the center of the ripple.
 * @param radius The current radius of the ripple cutout.
 */
private class RippleCutoutShape(
    private val offset: Offset,
    private val radius: Float,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                reset()
                addRect(Rect(0f, 0f, size.width, size.height), Path.Direction.Clockwise)
                addOval(Rect(center = offset, radius = radius))
                close()
            },
        )
    }
}

/**
 * Calculates the target radius for the ripple cutout animation in the theme transition.
 *
 * The target radius ensures that the ripple expands enough to cover the entire screen,
 * revealing the new theme.
 *
 * @param offset The offset of the theme switch button.
 * @return The calculated target radius.
 */
@Composable
private fun calculateTargetRadius(
    offset: Offset,
): Float {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    return remember(configuration.screenHeightDp, configuration.screenWidthDp, density) {
        with(density) {
            val height = configuration.screenHeightDp.dp.toPx()
            val width = configuration.screenWidthDp.dp.toPx()

            val y = max(offset.y, abs(height - offset.y))
            val x = max(offset.x, abs(width - offset.x))

            sqrt(x * x + y * y)
        }
    }
}

/**
 * Captures a screenshot of the view to be used in the theme transition animation.
 *
 * This function uses either PixelCopy (for Android Oreo and above) or Canvas drawing
 * (for older versions) to capture a screenshot of the current theme, before the transition.
 *
 * @param bounds The bounds of the area to capture.
 * @return A bitmap representing the screenshot, or null if capture failed.
 */
private suspend fun View.screenshot(bounds: Rect): Bitmap? = suspendCoroutine { continuation ->
    if (bounds.width == 0f || bounds.height == 0f) {
        logcat(tag = "ScreenshotScope", priority = LogPriority.WARN) {
            "Theme transition animation: Captured screen area has zero size. Ensure the layout containing ScreenshotScope is properly measured and has non-zero dimensions."
        }
        continuation.resume(null)
        return@suspendCoroutine
    }

    val bitmap = Bitmap.createBitmap(
        bounds.width.toInt(),
        bounds.height.toInt(),
        Bitmap.Config.ARGB_8888,
    )

    // Use hardware acceleration when available
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        try {
            val window = (this.context as Activity).window
            val srcRect = android.graphics.Rect(
                bounds.left.toInt(),
                bounds.top.toInt(),
                bounds.right.toInt(),
                bounds.bottom.toInt(),
            )
            val listener = OnPixelCopyFinishedListener { resultCode ->
                if (resultCode == PixelCopy.SUCCESS) {
                    continuation.resume(bitmap)
                } else {
                    continuation.resume(null)
                }
            }

            PixelCopy.request(window, srcRect, bitmap, listener, Handler(Looper.getMainLooper()))
        } catch (e: IllegalArgumentException) {
            logcat(tag = "ScreenshotScope", priority = LogPriority.ERROR) {
                "Screenshot capture failed: ${e.message}"
            }
            continuation.resumeWithException(e)
        } catch (e: SecurityException) {
            logcat(tag = "ScreenshotScope", priority = LogPriority.ERROR) {
                "Screenshot capture failed: ${e.message}"
            }
            continuation.resumeWithException(e)
        }
    } else {
        try {
            val canvas = Canvas(bitmap)
            draw(canvas)
            continuation.resume(bitmap)
        } catch (e: Exception) {
            logcat(tag = "ScreenshotScope", priority = LogPriority.ERROR) {
                "Screenshot capture failed: ${e.message}"
            }
            continuation.resumeWithException(e)
        }
    }
}
