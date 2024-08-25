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
import kotlinx.datetime.LocalDateTime

/**
 * Represents a single expense with its details.
 *
 * @property id The unique identifier for the expense.
 * @property amount The amount of the expense, including the currency.
 * @property categoryId The ID of the category this expense belongs to.
 * @property expenseDate The date and time when the expense occurred.
 * @property paymentMethod The payment method used for the expense.
 * @property notes Optional notes or comments about the expense.
 */
data class Expense(
    val id: Long,
    val amount: Amount,
    val categoryId: Long,
    val expenseDate: LocalDateTime,
    val paymentMethod: PaymentMethod,
    val notes: String? = null,
) {
    /**
     * An enum representing different payment methods.
     */
    enum class PaymentMethod {
        Cash,
        CreditCard,
        DebitCard,
        Other,
    }

    companion object {

        /**
         * A list of example expenses for testing purposes.
         */
        val exampleExpenses: List<Expense> = listOf(
            // Food category expenses
            Expense(
                id = 0,
                categoryId = 0,
                amount = Amount(35.80, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2023, 12, 28, 19, 45),
                paymentMethod = PaymentMethod.DebitCard,
                notes = "Dinner at Italian restaurant",
            ),
            Expense(
                id = 1,
                categoryId = 0,
                amount = Amount(12.50, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2024, 1, 2, 11, 15),
                paymentMethod = PaymentMethod.Cash,
                notes = "Coffee and pastry",
            ),
            Expense(
                id = 2,
                categoryId = 0,
                amount = Amount(60.00, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2024, 1, 8, 14, 30),
                paymentMethod = PaymentMethod.CreditCard,
                notes = "Weekly groceries",
            ),
            // Transport category expenses
            Expense(
                id = 3,
                categoryId = 1,
                amount = Amount(5.50, Currency.exampleCurrencies[1]),
                expenseDate = LocalDateTime(2024, 1, 5, 8, 0),
                paymentMethod = PaymentMethod.Cash,
                notes = "Subway ride",
            ),
            Expense(
                id = 4,
                categoryId = 1,
                amount = Amount(40.00, Currency.exampleCurrencies[1]),
                expenseDate = LocalDateTime(2023, 12, 20, 16, 20),
                paymentMethod = PaymentMethod.DebitCard,
                notes = "Fuel for car",
            ),
            Expense(
                id = 5,
                categoryId = 1,
                amount = Amount(150.00, Currency.exampleCurrencies[1]),
                expenseDate = LocalDateTime(2023, 12, 10, 10, 0),
                paymentMethod = PaymentMethod.Other,
                notes = "Flight ticket",
            ),
            // Entertainment category expenses
            Expense(
                id = 6,
                categoryId = 2,
                amount = Amount(25.00, Currency.exampleCurrencies[1]),
                expenseDate = LocalDateTime(2024, 1, 10, 20, 0),
                paymentMethod = PaymentMethod.CreditCard,
                notes = "Cinema tickets",
            ),
            Expense(
                id = 7,
                categoryId = 2,
                amount = Amount(10.00, Currency.exampleCurrencies[1]),
                expenseDate = LocalDateTime(2024, 1, 6, 14, 0),
                paymentMethod = PaymentMethod.Cash,
                notes = "Video game",
            ),
            Expense(
                id = 8,
                categoryId = 2,
                amount = Amount(7.50, Currency.exampleCurrencies[1]),
                expenseDate = LocalDateTime(2023, 12, 30, 16, 0),
                paymentMethod = PaymentMethod.DebitCard,
                notes = "Streaming subscription",
            ),
            // Shopping category expenses
            Expense(
                id = 9,
                categoryId = 3,
                amount = Amount(80.00, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2024, 1, 4, 15, 30),
                paymentMethod = PaymentMethod.DebitCard,
                notes = "New jacket",
            ),
            Expense(
                id = 10,
                categoryId = 3,
                amount = Amount(20.00, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2023, 12, 24, 11, 0),
                paymentMethod = PaymentMethod.Cash,
                notes = "Book",
            ),
            Expense(
                id = 11,
                categoryId = 3,
                amount = Amount(15.00, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2023, 12, 18, 17, 0),
                paymentMethod = PaymentMethod.CreditCard,
                notes = "Online purchase",
            ),
            // Housing category expenses
            Expense(
                id = 12,
                categoryId = 4,
                amount = Amount(1200.00, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2024, 1, 1, 10, 0),
                paymentMethod = PaymentMethod.Other,
                notes = "Monthly rent",
            ),
            Expense(
                id = 13,
                categoryId = 4,
                amount = Amount(60.00, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2023, 12, 22, 9, 0),
                paymentMethod = PaymentMethod.CreditCard,
                notes = "Electricity bill",
            ),
            Expense(
                id = 14,
                categoryId = 4,
                amount = Amount(30.00, Currency.exampleCurrencies[0]),
                expenseDate = LocalDateTime(2023, 12, 15, 14, 0),
                paymentMethod = PaymentMethod.DebitCard,
                notes = "Internet bill",
            ),
        )
    }
}
