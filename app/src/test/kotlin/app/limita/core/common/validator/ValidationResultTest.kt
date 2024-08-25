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
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationResultTest {

    @Test
    fun `Valid is a ValidationResult`() {
        ValidationResult.Valid.shouldBeInstanceOf<ValidationResult<*>>()
    }

    @Test
    fun `Invalid is a ValidationResult`() {
        val result = ValidationResult.Invalid(errors = listOf(Unit))
        result.shouldBeInstanceOf<ValidationResult<*>>()
    }

    @Test
    fun `withError adds error to Valid`() {
        val error = Any()
        val result = ValidationResult.Valid.withError(error)
        result.shouldBeInstanceOf<ValidationResult.Invalid<*>>()
        result.errors.shouldContainExactly(error)
    }

    @Test
    fun `withError adds error to Invalid`() {
        val error1 = Any()
        val error2 = Any()
        val result = ValidationResult.Invalid(listOf(error1)).withError(error2)
        result.shouldBeInstanceOf<ValidationResult.Invalid<*>>()
        result.errors.shouldContainExactly(error1, error2)
    }
}
