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

import app.limita.core.common.validator.ValidationResult.Valid

/**
 * Represents a function that validates an input value and returns a `ValidationResult` indicating whether the input is valid or invalid.
 *
 * @param Input The type of the input to be validated.
 * @param Error The type of the error that can occur during validation.
 */
fun interface InputValidator<in Input, out Error : Any> {
    /**
     * Validates the given input and returns a `ValidationResult` indicating whether the input is valid or invalid.
     *
     * @param input The input to be validated.
     * @return A `ValidationResult` indicating whether the input is valid or invalid.
     */
    fun validate(input: Input): ValidationResult<Error>

    /**
     * A companion object that provides a default implementation of `InputValidator` that always returns `Valid`.
     */
    companion object : InputValidator<Any?, Any> {
        override fun validate(input: Any?): ValidationResult<Any> = Valid
    }
}

/**
 * Composes an existing `InputValidator` with a provided predicate and error function to create a new `InputValidator`.
 *
 * @param predicate A function that takes an input and returns a boolean indicating whether the input is valid.
 * @param errorFactory A function that returns an error message or object in case the input is invalid.
 * @return A new `InputValidator` that combines the existing `InputValidator`'s validation logic with the provided predicate and error function.
 */
inline fun <Input, Error : Any> InputValidator<Input, Error>.compose(
    crossinline predicate: (Input) -> Boolean,
    crossinline errorFactory: () -> Error,
): InputValidator<Input, Error> = InputValidator { input ->
    if (predicate(input)) {
        validate(input)
    } else {
        val error = errorFactory()
        validate(input).withError(error)
    }
}
