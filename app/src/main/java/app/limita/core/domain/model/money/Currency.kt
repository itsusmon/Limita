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

package app.limita.core.domain.model.money

/**
 * Represents a currency with its code, symbol, and position of the symbol relative to the amount.
 *
 * @property code The ISO 4217 currency code (e.g., USD, EUR).
 * @property symbol The currency symbol (e.g., $, €).
 * @property position The position of the symbol relative to the amount (Start or End).
 */
data class Currency(
    val code: String,
    val symbol: String,
    val position: Position,
) {
    /**
     * An enum representing the position of the currency symbol.
     */
    enum class Position {
        Start,
        End,
    }

    /**
     * Formats a given amount with the currency symbol based on its position.
     *
     * @param amount The amount to format.
     * @return The formatted amount string.
     */
    fun format(amount: Double): String = when (position) {
        Position.Start -> "%s%.1f".format(symbol, amount)
        Position.End -> "%.1f%s".format(amount, symbol)
    }

    companion object {
        /**
         * A list of example currencies.
         */
        val exampleCurrencies = listOf(
            Currency("USD", "$", Position.Start), // US Dollar
            Currency("EUR", "€", Position.End), // Euro
            Currency("CNY", "¥", Position.Start), // Chinese Yuan
            Currency("JPY", "¥", Position.Start), // Japanese Yen
            Currency("RUB", "₽", Position.End), // Russian Ruble
        )
    }
}
