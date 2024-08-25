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

package app.limita.feature.overview.impl.screen.overview

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.limita.core.ui.ObserveSideEffects
import app.limita.feature.overview.impl.screen.overview.state.OverviewSideEffect.NavigateToSettings
import app.limita.feature.overview.impl.screen.overview.state.OverviewSideEffect.NavigateToStatistics
import app.limita.feature.settings.api.SettingsFeatureRoute
import app.limita.feature.statistics.api.StatisticsFeatureRoute
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
internal data object OverviewScreenRoute

internal fun NavGraphBuilder.installOverviewScreenRoute(navController: NavController) {
    composable<OverviewScreenRoute> {
        val viewModel: OverviewViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        ObserveSideEffects(viewModel.sideEffects) { sideEffect ->
            when (sideEffect) {
                NavigateToSettings -> navController.navigate(SettingsFeatureRoute)
                NavigateToStatistics -> navController.navigate(StatisticsFeatureRoute)
            }
        }

        OverviewScreen(
            state = state,
            onAction = viewModel::onAction,
        )
    }
}

