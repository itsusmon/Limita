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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.limita.core.common.ext.flowOf
import app.limita.core.domain.repository.theme.ThemeRepository
import app.limita.core.domain.repository.user.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

internal class MainViewModel(
    themeRepository: ThemeRepository,
    userPreferences: UserPreferencesRepository,
) : ViewModel() {

    val appThemeFlow = themeRepository.appThemeFlow
        .stateIn(viewModelScope, WhileSubscribed(5_000), null)

    val darkModeFlow = themeRepository.darkModeFlow
        .stateIn(viewModelScope, WhileSubscribed(5_000), null)

    val contrastModeFlow = themeRepository.contrastModeFlow
        .stateIn(viewModelScope, WhileSubscribed(5_000), null)

    val isOnboardingCompletedFlow = flowOf(userPreferences::isOnboardingCompleted)
        .stateIn(viewModelScope, WhileSubscribed(5_000), null)

    fun shouldKeepSplashScreen(): Boolean {
        return appThemeFlow.value == null
            || darkModeFlow.value == null
            || contrastModeFlow.value == null
            || isOnboardingCompletedFlow.value == null
    }
}
