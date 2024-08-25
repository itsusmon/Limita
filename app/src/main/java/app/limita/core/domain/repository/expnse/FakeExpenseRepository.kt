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

import app.limita.core.domain.model.expense.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeExpenseRepository : ExpenseRepository {

    override val expensesFlow = MutableStateFlow(Expense.exampleExpenses)

    override suspend fun getExpenseById(expenseId: Long): Expense? {
        return expensesFlow.value.find { it.id == expenseId }
    }

    override fun getExpensesByCategory(categoryId: Long): Flow<List<Expense>> {
        return expensesFlow.map { expenses -> expenses.filter { it.categoryId == categoryId } }
    }

    override suspend fun saveExpense(expense: Expense) {
        expensesFlow.update { expenses ->
            val hasExpense = expenses.any { it.id == expense.id }
            if (hasExpense) {
                expenses.map { if (it.id == expense.id) expense else it }
            } else {
                expenses + expense
            }
        }
    }

    override suspend fun deleteExpense(expense: Expense) {
        expensesFlow.update { expenses ->
            expenses.filter { it.id != expense.id }
        }
    }
}
