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

package app.limita.android

import android.graphics.Color
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle.Companion.auto
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import app.limita.android.navigation.LimitaNavHost
import app.limita.core.domain.model.theme.AppTheme
import app.limita.core.domain.model.theme.ContrastMode
import app.limita.core.domain.model.theme.DarkMode
import app.limita.core.ui.theme.LimitaTheme
import app.limita.core.ui.theme.asBoolean
import app.limita.feature.onboarding.api.OnboardingFeatureRoute
import app.limita.feature.overview.api.OverviewFeatureRoute
import org.koin.androidx.viewmodel.ext.android.viewModel

private val LightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val DarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition(viewModel::shouldKeepSplashScreen)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val appTheme by viewModel.appThemeFlow.collectAsStateWithLifecycle()
            val darkMode by viewModel.darkModeFlow.collectAsStateWithLifecycle()
            val contrastMode by viewModel.contrastModeFlow.collectAsStateWithLifecycle()
            val isOnboardingCompleted by viewModel.isOnboardingCompletedFlow.collectAsStateWithLifecycle()

            val isDarkMode = darkMode.asBoolean()
            DisposableEffect(isDarkMode) {
                enableEdgeToEdge(
                    statusBarStyle = auto(TRANSPARENT, TRANSPARENT) { isDarkMode },
                    navigationBarStyle = auto(LightScrim, DarkScrim) { isDarkMode },
                )
                onDispose { }
            }

            LimitaTheme(
                appTheme = appTheme ?: AppTheme.Default,
                darkMode = darkMode ?: DarkMode.FollowSystem,
                contrastMode = contrastMode ?: ContrastMode.Standard,
            ) {
                val navController = rememberNavController()
                val startDestination: Any = when (isOnboardingCompleted) {
                    true -> OverviewFeatureRoute
                    false -> OnboardingFeatureRoute
                    else -> return@LimitaTheme
                }

                LimitaNavHost(
                    navController = navController,
                    startDestination = startDestination,
                )
            }
        }
    }
}
