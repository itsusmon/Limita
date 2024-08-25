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

package app.limita.core.domain.repository.theme

import app.limita.core.domain.model.theme.AppTheme
import app.limita.core.domain.model.theme.ContrastMode
import app.limita.core.domain.model.theme.DarkMode
import kotlinx.coroutines.flow.MutableStateFlow

class FakeThemeRepository : ThemeRepository {

    override val appThemeFlow = MutableStateFlow(AppTheme.Default)

    override val darkModeFlow = MutableStateFlow(DarkMode.FollowSystem)

    override val contrastModeFlow = MutableStateFlow(ContrastMode.Standard)

    override suspend fun setAppTheme(appTheme: AppTheme) {
        appThemeFlow.emit(appTheme)
    }

    override suspend fun setDarkMode(darkMode: DarkMode) {
        darkModeFlow.emit(darkMode)
    }

    override suspend fun setContrastMode(contrastMode: ContrastMode) {
        contrastModeFlow.emit(contrastMode)
    }
}
