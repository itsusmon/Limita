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

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import app.limita.core.domain.model.theme.ContrastMode
import app.limita.core.domain.model.theme.ContrastMode.High
import app.limita.core.domain.model.theme.ContrastMode.Medium
import app.limita.core.domain.model.theme.ContrastMode.Standard

private val seed = Color(0xFF18C480)

private val lightStandardContrastColorScheme = lightColorScheme(
    primary = Color(0xFF006D44),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF2FD08A),
    onPrimaryContainer = Color(0xFF00321D),
    secondary = Color(0xFF3B4F41),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF5E7464),
    onSecondaryContainer = Color(0xFFFFFFFF),
    tertiary = Color(0xFF26505C),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF4C7581),
    onTertiaryContainer = Color(0xFFFFFFFF),
    error = Color(0xFF98000B),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFD02C28),
    onErrorContainer = Color(0xFFFFFFFF),
    background = Color(0xFFF3FCF3),
    onBackground = Color(0xFF161D18),
    surface = Color(0xFFF3FCF3),
    onSurface = Color(0xFF161D18),
    surfaceVariant = Color(0xFFD7E6DA),
    onSurfaceVariant = Color(0xFF3C4A41),
    outline = Color(0xFF6C7B70),
    outlineVariant = Color(0xFFBBCABE),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF2B322D),
    inverseOnSurface = Color(0xFFEBF3EA),
    inversePrimary = Color(0xFF46E099),
    surfaceDim = Color(0xFFD4DCD4),
    surfaceBright = Color(0xFFF3FCF3),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFEEF6ED),
    surfaceContainer = Color(0xFFE8F0E8),
    surfaceContainerHigh = Color(0xFFE2EAE2),
    surfaceContainerHighest = Color(0xFFDDE5DC),
)

private val lightMediumContrastColorScheme = lightColorScheme(
    primary = Color(0xFF004D2F),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF008655),
    onPrimaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFF33473A),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF5E7464),
    onSecondaryContainer = Color(0xFFFFFFFF),
    tertiary = Color(0xFF1D4854),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF4C7581),
    onTertiaryContainer = Color(0xFFFFFFFF),
    error = Color(0xFF8C0009),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFD02C28),
    onErrorContainer = Color(0xFFFFFFFF),
    background = Color(0xFFF3FCF3),
    onBackground = Color(0xFF161D18),
    surface = Color(0xFFF3FCF3),
    onSurface = Color(0xFF161D18),
    surfaceVariant = Color(0xFFD7E6DA),
    onSurfaceVariant = Color(0xFF38463D),
    outline = Color(0xFF546258),
    outlineVariant = Color(0xFF707E73),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF2B322D),
    inverseOnSurface = Color(0xFFEBF3EA),
    inversePrimary = Color(0xFF46E099),
    surfaceDim = Color(0xFFD4DCD4),
    surfaceBright = Color(0xFFF3FCF3),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFEEF6ED),
    surfaceContainer = Color(0xFFE8F0E8),
    surfaceContainerHigh = Color(0xFFE2EAE2),
    surfaceContainerHighest = Color(0xFFDDE5DC),
)

private val lightHighContrastColorScheme = lightColorScheme(
    primary = Color(0xFF002816),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF004D2F),
    onPrimaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFF13261A),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF33473A),
    onSecondaryContainer = Color(0xFFFFFFFF),
    tertiary = Color(0xFF00262F),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF1D4854),
    onTertiaryContainer = Color(0xFFFFFFFF),
    error = Color(0xFF4E0002),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFF8C0009),
    onErrorContainer = Color(0xFFFFFFFF),
    background = Color(0xFFF3FCF3),
    onBackground = Color(0xFF161D18),
    surface = Color(0xFFF3FCF3),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFD7E6DA),
    onSurfaceVariant = Color(0xFF1A271F),
    outline = Color(0xFF38463D),
    outlineVariant = Color(0xFF38463D),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF2B322D),
    inverseOnSurface = Color(0xFFFFFFFF),
    inversePrimary = Color(0xFFA5FFCA),
    surfaceDim = Color(0xFFD4DCD4),
    surfaceBright = Color(0xFFF3FCF3),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFEEF6ED),
    surfaceContainer = Color(0xFFE8F0E8),
    surfaceContainerHigh = Color(0xFFE2EAE2),
    surfaceContainerHighest = Color(0xFFDDE5DC),
)

private val darkStandardContrastColorScheme = darkColorScheme(
    primary = Color(0xFF4EE69E),
    onPrimary = Color(0xFF003921),
    primaryContainer = Color(0xFF00BB79),
    onPrimaryContainer = Color(0xFF001E0F),
    secondary = Color(0xFFB5CCBA),
    onSecondary = Color(0xFF213528),
    secondaryContainer = Color(0xFF455A4B),
    onSecondaryContainer = Color(0xFFE7FFEB),
    tertiary = Color(0xFFA3CDDB),
    onTertiary = Color(0xFF033641),
    tertiaryContainer = Color(0xFF325B67),
    onTertiaryContainer = Color(0xFFF0FBFF),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFFAE0E13),
    onErrorContainer = Color(0xFFFFFAF9),
    background = Color(0xFF0E1510),
    onBackground = Color(0xFFDDE5DC),
    surface = Color(0xFF0E1510),
    onSurface = Color(0xFFDDE5DC),
    surfaceVariant = Color(0xFF3C4A41),
    onSurfaceVariant = Color(0xFFBBCABE),
    outline = Color(0xFF869489),
    outlineVariant = Color(0xFF3C4A41),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFFDDE5DC),
    inverseOnSurface = Color(0xFF2B322D),
    inversePrimary = Color(0xFF006D44),
    surfaceDim = Color(0xFF0E1510),
    surfaceBright = Color(0xFF333B35),
    surfaceContainerLowest = Color(0xFF09100B),
    surfaceContainerLow = Color(0xFF161D18),
    surfaceContainer = Color(0xFF1A211C),
    surfaceContainerHigh = Color(0xFF242C26),
    surfaceContainerHighest = Color(0xFF2F3731),
)

private val darkMediumContrastColorScheme = darkColorScheme(
    primary = Color(0xFF4EE69E),
    onPrimary = Color(0xFF001D0F),
    primaryContainer = Color(0xFF00BB79),
    onPrimaryContainer = Color(0xFF000000),
    secondary = Color(0xFFB9D1BE),
    onSecondary = Color(0xFF061A0F),
    secondaryContainer = Color(0xFF809685),
    onSecondaryContainer = Color(0xFF000000),
    tertiary = Color(0xFFA7D1DF),
    onTertiary = Color(0xFF001920),
    tertiaryContainer = Color(0xFF6E97A4),
    onTertiaryContainer = Color(0xFF000000),
    error = Color(0xFFFFBAB1),
    onError = Color(0xFF370001),
    errorContainer = Color(0xFFFF5449),
    onErrorContainer = Color(0xFF000000),
    background = Color(0xFF0E1510),
    onBackground = Color(0xFFDDE5DC),
    surface = Color(0xFF0E1510),
    onSurface = Color(0xFFF5FDF4),
    surfaceVariant = Color(0xFF3C4A41),
    onSurfaceVariant = Color(0xFFBFCFC2),
    outline = Color(0xFF98A79B),
    outlineVariant = Color(0xFF78877C),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFFDDE5DC),
    inverseOnSurface = Color(0xFF242C26),
    inversePrimary = Color(0xFF005333),
    surfaceDim = Color(0xFF0E1510),
    surfaceBright = Color(0xFF333B35),
    surfaceContainerLowest = Color(0xFF09100B),
    surfaceContainerLow = Color(0xFF161D18),
    surfaceContainer = Color(0xFF1A211C),
    surfaceContainerHigh = Color(0xFF242C26),
    surfaceContainerHighest = Color(0xFF2F3731),
)

private val darkHighContrastColorScheme = darkColorScheme(
    primary = Color(0xFFEEFFF1),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF4CE49D),
    onPrimaryContainer = Color(0xFF000000),
    secondary = Color(0xFFEFFFF0),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFFB9D1BE),
    onSecondaryContainer = Color(0xFF000000),
    tertiary = Color(0xFFF5FCFF),
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFFA7D1DF),
    onTertiaryContainer = Color(0xFF000000),
    error = Color(0xFFFFF9F9),
    onError = Color(0xFF000000),
    errorContainer = Color(0xFFFFBAB1),
    onErrorContainer = Color(0xFF000000),
    background = Color(0xFF0E1510),
    onBackground = Color(0xFFDDE5DC),
    surface = Color(0xFF0E1510),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF3C4A41),
    onSurfaceVariant = Color(0xFFEFFFF1),
    outline = Color(0xFFBFCFC2),
    outlineVariant = Color(0xFFBFCFC2),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFFDDE5DC),
    inverseOnSurface = Color(0xFF000000),
    inversePrimary = Color(0xFF00311C),
    surfaceDim = Color(0xFF0E1510),
    surfaceBright = Color(0xFF333B35),
    surfaceContainerLowest = Color(0xFF09100B),
    surfaceContainerLow = Color(0xFF161D18),
    surfaceContainer = Color(0xFF1A211C),
    surfaceContainerHigh = Color(0xFF242C26),
    surfaceContainerHighest = Color(0xFF2F3731),
)

internal fun greenColorScheme(
    isDarkMode: Boolean,
    contrastMode: ContrastMode,
): ColorScheme {
    return when (contrastMode) {
        Standard -> if (isDarkMode) darkStandardContrastColorScheme else lightStandardContrastColorScheme
        Medium -> if (isDarkMode) darkMediumContrastColorScheme else lightMediumContrastColorScheme
        High -> if (isDarkMode) darkHighContrastColorScheme else lightHighContrastColorScheme
    }
}
