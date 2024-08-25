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

package app.limita.core.domain.repository.di

import app.limita.core.domain.repository.expnse.ExpenseCategoryRepository
import app.limita.core.domain.repository.expnse.ExpenseRepository
import app.limita.core.domain.repository.expnse.FakeExpenseCategoryRepository
import app.limita.core.domain.repository.expnse.FakeExpenseRepository
import app.limita.core.domain.repository.theme.FakeThemeRepository
import app.limita.core.domain.repository.theme.ThemeRepository
import app.limita.core.domain.repository.user.FakeUserPreferencesRepository
import app.limita.core.domain.repository.user.UserPreferencesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val RepositoryModule = module {
    singleOf<ThemeRepository>(::FakeThemeRepository)
    singleOf<UserPreferencesRepository>(::FakeUserPreferencesRepository)

    singleOf<ExpenseRepository>(::FakeExpenseRepository)
    singleOf<ExpenseCategoryRepository>(::FakeExpenseCategoryRepository)
}
