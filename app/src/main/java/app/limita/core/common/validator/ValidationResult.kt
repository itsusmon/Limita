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

package app.limita.core.common.validator

import app.limita.core.common.validator.ValidationResult.Invalid
import app.limita.core.common.validator.ValidationResult.Valid

/**
 * Represents the result of a validation operation.
 *
 * @param Error The type of the error that can occur during validation.
 */
sealed interface ValidationResult<out Error : Any> {

    /**
     * Indicates a successful validation with no errors.
     */
    data object Valid : ValidationResult<Nothing>

    /**
     * Represents a failed validation with a list of errors.
     *
     * Note: The errors list cannot be empty, in that case validation result should be Valid.
     *
     * @param errors The list of errors.
     * @throws IllegalArgumentException When the [errors] list empty.
     */
    @JvmInline
    value class Invalid<out Error : Any>(val errors: List<Error>) : ValidationResult<Error> {

        init {
            require(errors.isNotEmpty()) { "Invalid validation result: The list of errors cannot be empty" }
        }
    }
}

/**
 * Adds an error to an existing [ValidationResult].
 *
 * @param error The error to add.
 * @return An [Invalid] instance containing the original errors and the newly added error.
 */
fun <Error : Any> ValidationResult<Error>.withError(error: Error): Invalid<Error> = when (this) {
    Valid -> Invalid(errors = listOf(error))
    is Invalid -> Invalid(errors + error)
}
