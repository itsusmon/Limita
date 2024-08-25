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

package app.limita.core.domain.model.expense

import app.limita.core.domain.model.money.Amount
import app.limita.core.domain.model.money.Currency

/**
 * Represents a category of expenses with its ID, name, amount spent, and total amount.
 *
 * @property id The unique identifier for the expense category.
 * @property name The name of the expense category.
 * @property spent The total amount spent in this category.
 * @property total The total budget for this category.
 */
data class ExpenseCategory(
    val id: Long,
    val name: String,
    val description: String,
    val spent: Amount,
    val total: Amount,
) {

    companion object {

        /**
         * A list of example expense categories.
         */
        val exampleExpenseCategories: List<ExpenseCategory> = listOf(
            ExpenseCategory(
                id = 0,
                name = "Food",
                description = "Groceries, dining out, snacks, drinks",
                spent = Amount(
                    value = 113.30,
                    currency = Currency.exampleCurrencies[0],
                ),
                total = Amount(
                    value = 300.0,
                    currency = Currency.exampleCurrencies[0],
                ),
            ),
            ExpenseCategory(
                id = 1,
                name = "Transport",
                description = "Public transportation, gas, car maintenance, taxis",
                spent = Amount(
                    value = 205.50,
                    currency = Currency.exampleCurrencies[1],
                ),
                total = Amount(
                    value = 300.0,
                    currency = Currency.exampleCurrencies[1],
                ),
            ),
            ExpenseCategory(
                id = 2,
                name = "Entertainment",
                description = "Movies, concerts, games, streaming services",
                spent = Amount(
                    value = 42.50,
                    currency = Currency.exampleCurrencies[1],
                ),
                total = Amount(
                    value = 200.0,
                    currency = Currency.exampleCurrencies[1],
                ),
            ),
            ExpenseCategory(
                id = 3,
                name = "Shopping",
                description = "Clothes, electronics, gifts, online purchases",
                spent = Amount(
                    value = 115.00,
                    currency = Currency.exampleCurrencies[0],
                ),
                total = Amount(
                    value = 500.0,
                    currency = Currency.exampleCurrencies[0],
                ),
            ),
            ExpenseCategory(
                id = 4,
                name = "Housing",
                description = "Rent or mortgage, utilities, property taxes",
                spent = Amount(
                    value = 1390.00,
                    currency = Currency.exampleCurrencies[0],
                ),
                total = Amount(
                    value = 1500.0,
                    currency = Currency.exampleCurrencies[0],
                ),
            ),
        )
    }
}
