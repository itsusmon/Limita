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

/**
 * Composes an existing `InputValidator` with a regular expression to validate input.
 *
 * @param regex The regular expression to match against the input.
 * @param errorFactory A function that returns an error message or object in case the input doesn't match the regular expression.
 * @return A new `InputValidator` that combines the existing `InputValidator`'s validation logic with the regular expression check.
 */
inline fun <Input : CharSequence, Error : Any> InputValidator<Input, Error>.matches(
    regex: Regex,
    crossinline errorFactory: () -> Error,
) = compose(
    predicate = regex::matches,
    errorFactory = errorFactory,
)

/**
 * Composes an existing `InputValidator` to check if the input's length is less than or equal to a specified length.
 *
 * @param length The maximum allowed length of the input.
 * @param errorFactory A function that returns an error message or object in case the input's length exceeds the maximum.
 * @return A new `InputValidator` that combines the existing `InputValidator`'s validation logic with the length check.
 */
inline fun <Input : CharSequence, Error : Any> InputValidator<Input, Error>.lengthLessThan(
    length: Int,
    crossinline errorFactory: () -> Error,
) = compose(
    predicate = { input -> input.length <= length },
    errorFactory = errorFactory,
)

/**
 * Composes an existing `InputValidator` to check if the input's length is greater than or equal to a specified length.
 *
 * @param length The minimum required length of the input.
 * @param errorFactory A function that returns an error message or object in case the input's length is less than the minimum.
 * @return A new `InputValidator` that combines the existing `InputValidator`'s validation logic with the length check.
 */
inline fun <Input : CharSequence, Error : Any> InputValidator<Input, Error>.lengthGreaterThan(
    length: Int,
    crossinline errorFactory: () -> Error,
) = compose(
    predicate = { input -> input.length >= length },
    errorFactory = errorFactory,
)

/**
 * Composes an existing `InputValidator` to check if the input starts with a specified prefix.
 *
 * @param prefix The prefix to check for at the beginning of the input.
 * @param errorFactory A function that returns an error message or object in case the input doesn't start with the prefix.
 * @return A new `InputValidator` that combines the existing `InputValidator`'s validation logic with the prefix check.
 */
inline fun <Input : CharSequence, Error : Any> InputValidator<Input, Error>.startsWith(
    prefix: CharSequence,
    crossinline errorFactory: () -> Error,
) = compose(
    predicate = { input -> input.startsWith(prefix) },
    errorFactory = errorFactory,
)

/**
 * Composes an existing `InputValidator` to check if the input ends with a specified suffix.
 *
 * @param suffix The suffix to check for at the end of the input.
 * @param errorFactory A function that returns an error message or object in case the input doesn't end with the suffix.
 * @return A new `InputValidator` that combines the existing `InputValidator`'s validation logic with the suffix check.
 */
inline fun <Input : CharSequence, Error : Any> InputValidator<Input, Error>.endsWith(
    suffix: CharSequence,
    crossinline errorFactory: () -> Error,
) = compose(
    predicate = { input -> input.endsWith(suffix) },
    errorFactory = errorFactory,
)
