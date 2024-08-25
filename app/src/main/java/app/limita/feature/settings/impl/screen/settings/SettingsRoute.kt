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

package app.limita.feature.settings.impl.screen.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.limita.core.ui.ObserveSideEffects
import app.limita.feature.about.api.AboutFeatureRoute
import app.limita.feature.settings.impl.screen.settings.state.SettingsSideEffect
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object SettingsScreenRoute

internal fun NavGraphBuilder.installSettingsScreenRoute(navController: NavController) {
    composable<SettingsScreenRoute> {
        val viewModel: SettingsViewModel = koinViewModel()

        ObserveSideEffects(viewModel.sideEffects) { sideEffect ->
            when (sideEffect) {
                SettingsSideEffect.NavigateBack -> navController.navigateUp()
                SettingsSideEffect.NavigateToAbout -> navController.navigate(AboutFeatureRoute)
            }
        }

        SettingsScreen(
            onAction = viewModel::onAction,
        )
    }
}
