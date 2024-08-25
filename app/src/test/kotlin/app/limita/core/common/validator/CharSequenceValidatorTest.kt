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

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharSequenceValidatorTest {

    @Test
    fun `matches returns valid result when input matches regex`() {
        val inputValidator = InputValidator
            .matches(regex = Regex("[a-z]+")) { throw AssertionError("Should not be called") }

        val result = inputValidator.validate("abc")
        result shouldBeSameInstanceAs ValidationResult.Valid
    }

    @Test
    fun `matches returns invalid result when input does not match regex`() {
        val error = Any()
        val inputValidator = InputValidator
            .matches(regex = Regex("[a-z]+")) { error }
        val result = inputValidator.validate("123")

        result.shouldBeInstanceOf<ValidationResult.Invalid<*>>()
        result.errors.shouldContainExactly(error)
    }

    @Test
    fun `lengthLessThan returns valid result when input length is less than specified length`() {
        val inputValidator = InputValidator
            .lengthLessThan(length = 5) { throw AssertionError("Should not be called") }
        val result = inputValidator.validate("abcd")
        result shouldBeSameInstanceAs ValidationResult.Valid
    }

    @Test
    fun `lengthLessThan returns invalid result when input length is greater than specified length`() {
        val error = Any()
        val inputValidator = InputValidator
            .lengthLessThan(length = 5) { error }
        val result = inputValidator.validate("abcdef")

        result.shouldBeInstanceOf<ValidationResult.Invalid<*>>()
        result.errors.shouldContainExactly(error)
    }

    @Test
    fun `lengthGreaterThan returns valid result when input length is greater than specified length`() {
        val inputValidator = InputValidator
            .lengthGreaterThan(length = 5) { throw AssertionError("Should not be called") }
        val result = inputValidator.validate("abcdef")
        result shouldBeSameInstanceAs ValidationResult.Valid
    }

    @Test
    fun `lengthGreaterThan returns invalid result when input length is less than specified length`() {
        val error = Any()
        val inputValidator = InputValidator
            .lengthGreaterThan(length = 5) { error }
        val result = inputValidator.validate("abcd")

        result.shouldBeInstanceOf<ValidationResult.Invalid<*>>()
        result.errors.shouldContainExactly(error)
    }

    @Test
    fun `startsWith returns valid result when input starts with prefix`() {
        val inputValidator = InputValidator
            .startsWith(prefix = "abc") { throw AssertionError("Should not be called") }
        val result = inputValidator.validate("abcdef")
        result shouldBeSameInstanceAs ValidationResult.Valid
    }

    @Test
    fun `startsWith returns invalid result when input does not start with prefix`() {
        val error = Any()
        val inputValidator = InputValidator
            .startsWith(prefix = "abc") { error }
        val result = inputValidator.validate("defabc")

        result.shouldBeInstanceOf<ValidationResult.Invalid<*>>()
        result.errors.shouldContainExactly(error)
    }

    @Test
    fun `endsWith returns valid result when input ends with suffix`() {
        val inputValidator = InputValidator
            .endsWith(suffix = "def") { throw AssertionError("Should not be called") }
        val result = inputValidator.validate("abcdef")
        result shouldBeSameInstanceAs ValidationResult.Valid
    }

    @Test
    fun `endsWith returns invalid result when input does not end with suffix`() {
        val error = Any()
        val inputValidator = InputValidator.endsWith(suffix = "def") { error }
        val result = inputValidator.validate("defabc")

        result.shouldBeInstanceOf<ValidationResult.Invalid<*>>()
        result.errors.shouldContainExactly(error)
    }
}
