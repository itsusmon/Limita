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

package app.limita.core.domain.repository.expnse

import app.limita.core.domain.model.expense.ExpenseCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeExpenseCategoryRepository : ExpenseCategoryRepository {

    override val expenseCategoriesFlow = MutableStateFlow(ExpenseCategory.exampleExpenseCategories)

    override suspend fun saveExpenseCategory(category: ExpenseCategory) {
        expenseCategoriesFlow.update { items ->
            val hasItem = items.any { it.id == category.id }
            if (hasItem) {
                items.map { item -> if (item.id == category.id) category else item }
            } else {
                items + category
            }
        }
    }

    override suspend fun deleteExpenseCategory(category: ExpenseCategory) {
        expenseCategoriesFlow.update { items ->
            items.filter { item -> item.id != category.id }
        }
    }
}
