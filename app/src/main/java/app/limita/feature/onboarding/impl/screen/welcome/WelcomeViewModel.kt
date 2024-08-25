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

package app.limita.feature.onboarding.impl.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.limita.core.domain.model.theme.DarkMode
import app.limita.core.domain.repository.theme.ThemeRepository
import app.limita.core.domain.repository.user.UserPreferencesRepository
import app.limita.feature.onboarding.impl.screen.welcome.state.WelcomeAction
import app.limita.feature.onboarding.impl.screen.welcome.state.WelcomeSideEffect
import app.limita.feature.onboarding.impl.screen.welcome.state.WelcomeViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class WelcomeViewModel(
    private val themeRepository: ThemeRepository,
    private val userPreferences: UserPreferencesRepository,
) : ViewModel() {

    val state = themeRepository.darkModeFlow
        .map(::WelcomeViewState)
        .stateIn(viewModelScope, WhileSubscribed(5_000), WelcomeViewState())

    private val sideEffectChannel = Channel<WelcomeSideEffect>()
    val sideEffects: Flow<WelcomeSideEffect> = sideEffectChannel.receiveAsFlow()

    fun onAction(action: WelcomeAction) = when (action) {
        WelcomeAction.ToggleDarkMode -> toggleDarkMode()
        WelcomeAction.ToggleLightMode -> toggleLightMode()
        WelcomeAction.CompleteOnboarding -> completeOnboarding()
    }

    private fun toggleDarkMode() = viewModelScope.launch {
        themeRepository.setDarkMode(DarkMode.Dark)
    }

    private fun toggleLightMode() = viewModelScope.launch {
        themeRepository.setDarkMode(DarkMode.Light)
    }

    private fun completeOnboarding() = viewModelScope.launch {
        userPreferences.setOnboardingCompleted(completed = true)
        sideEffectChannel.send(WelcomeSideEffect.NavigateToOverview)
    }
}
