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

package app.limita.core.ui.theme

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import app.limita.core.common.SystemUtils.isDynamicColorSupported
import app.limita.core.domain.model.theme.AppTheme
import app.limita.core.domain.model.theme.ContrastMode
import app.limita.core.domain.model.theme.DarkMode
import logcat.LogPriority
import logcat.logcat

// Note: The default values are only used for previews.
@Composable
fun LimitaTheme(
    appTheme: AppTheme = AppTheme.Default,
    darkMode: DarkMode = DarkMode.FollowSystem,
    contrastMode: ContrastMode = ContrastMode.Standard,
    content: @Composable () -> Unit,
) {
    val isDarkMode = darkMode.asBoolean()
    val colorScheme = when {
        isDynamicColorSupported && appTheme == AppTheme.MaterialYou -> dynamicColorScheme(isDarkMode)
        appTheme == AppTheme.Green -> greenColorScheme(isDarkMode, contrastMode)
        else -> {
            logcat(tag = "AppTheme", priority = LogPriority.ERROR) {
                """Theme initialization failed: Unsupported app theme: $appTheme.
                  |Falling back to green color scheme. Ensure that the 'appTheme' setting is valid.
                  |This may result in unexpected theme behaviors.""".trimMargin()
            }
            greenColorScheme(isDarkMode, contrastMode)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
    ) {
        Surface(content = content)
    }
}

@Composable
fun DarkMode?.asBoolean() = when (this) {
    DarkMode.Dark -> true
    DarkMode.Light -> false
    else -> isSystemInDarkTheme()
}

@Composable
@RequiresApi(VERSION_CODES.S)
private fun dynamicColorScheme(isDarkMode: Boolean): ColorScheme {
    val context = LocalContext.current
    return if (isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
}
