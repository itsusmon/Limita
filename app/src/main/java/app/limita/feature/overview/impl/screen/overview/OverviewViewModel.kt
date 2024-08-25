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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.limita.core.domain.repository.expnse.ExpenseCategoryRepository
import app.limita.feature.overview.impl.screen.overview.state.OverviewAction
import app.limita.feature.overview.impl.screen.overview.state.OverviewAction.NavigateToSettings
import app.limita.feature.overview.impl.screen.overview.state.OverviewAction.NavigateToStatistics
import app.limita.feature.overview.impl.screen.overview.state.OverviewSideEffect
import app.limita.feature.overview.impl.screen.overview.state.OverviewViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class OverviewViewModel(
    expenseCategoryRepository: ExpenseCategoryRepository,
) : ViewModel() {

    val state = expenseCategoryRepository.expenseCategoriesFlow
        .map(::OverviewViewState)
        .stateIn(viewModelScope, WhileSubscribed(5000), OverviewViewState())

    private val sideEffectChannel = Channel<OverviewSideEffect>()
    val sideEffects: Flow<OverviewSideEffect> = sideEffectChannel.receiveAsFlow()

    fun onAction(action: OverviewAction) = when (action) {
        NavigateToSettings -> navigateToSettings()
        NavigateToStatistics -> navigateToStatistics()
    }

    private fun navigateToSettings() = viewModelScope.launch {
        sideEffectChannel.send(OverviewSideEffect.NavigateToSettings)
    }

    private fun navigateToStatistics() = viewModelScope.launch {
        sideEffectChannel.send(OverviewSideEffect.NavigateToStatistics)
    }
}
