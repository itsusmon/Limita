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
class InputValidatorTest {

    @Test
    fun `empty input validator returns valid result`() {
        val inputValidator = InputValidator
        val result = inputValidator.validate(Unit)
        result shouldBeSameInstanceAs ValidationResult.Valid
    }

    @Test
    fun `error block should not be called when the predicate returns true`() {
        val inputValidator = InputValidator.compose(
            predicate = { true },
            errorFactory = { throw AssertionError("The error block should not be called when the predicate returns true") },
        )
        val result = inputValidator.validate(Unit)
        result shouldBeSameInstanceAs ValidationResult.Valid
    }

    @Test
    fun `error block returns correct error when the predicate returns false`() {
        val error = Any()
        val inputValidator = InputValidator.compose(
            predicate = { false },
            errorFactory = { error },
        )
        val result = inputValidator.validate(Unit)

        result.shouldBeInstanceOf<ValidationResult.Invalid<Any>>()
        result.errors.shouldContainExactly(error)
    }
}
