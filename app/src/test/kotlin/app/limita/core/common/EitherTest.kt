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

package app.limita.core.common

import app.limita.core.common.Either.Failure
import app.limita.core.common.Either.Success
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.CancellationException
import org.junit.Test

class EitherTest {

    @Test
    fun `asSuccess returns Success`() {
        val result = "success".asSuccess()
        result.shouldBeInstanceOf<Success<String>>()
        result.result shouldBe "success"
    }

    @Test
    fun `asFailure returns Failure`() {
        val result = "error".asFailure()
        result.shouldBeInstanceOf<Failure<String>>()
        result.cause shouldBe "error"
    }

    @Test
    fun `catch returns Success when block succeeds`() {
        val result = catch { "success" }
        result.shouldBeInstanceOf<Success<String>>()
        result.result shouldBe "success"
    }

    @Test
    fun `catch returns Failure when block throws exception`() {
        val result = catch { throw RuntimeException("error") }
        result.shouldBeInstanceOf<Failure<*>>()
        result.cause shouldBe RuntimeException("error")
    }

    @Test
    fun `isSuccess returns true for Success`() {
        val result = Success("success")
        result.isSuccess().shouldBeTrue()
        result.isFailure().shouldBeFalse()
    }

    @Test
    fun `isFailure returns true for Failure`() {
        val result = Failure("error")
        result.isFailure().shouldBeTrue()
        result.isSuccess().shouldBeFalse()
    }

    @Test
    fun `fold returns onSuccess result for Success`() {
        val result = Success("success").fold(
            onSuccess = { "$it!" },
            onFailure = { "error" },
        )
        result shouldBe "success!"
    }

    @Test
    fun `fold returns onFailure result for Failure`() {
        val result = Failure("error").fold(
            onSuccess = { "success" },
            onFailure = { "$it!" },
        )
        result shouldBe "error!"
    }

    @Test
    fun `map transformsSuccess value`() {
        val result = Success("success").map(String::uppercase)
        result.shouldBeInstanceOf<Success<String>>()
        result.result shouldBe "SUCCESS"
    }

    @Test
    fun `map does not affect Failure`() {
        val result = Failure("error").map(String::uppercase)
        result.shouldBeInstanceOf<Failure<String>>()
        result.cause shouldBe "error"
    }

    @Test
    fun `mapFailure transforms Failure cause`() {
        val result = Failure("error").mapFailure(String::uppercase)
        result.shouldBeInstanceOf<Failure<String>>()
        result.cause shouldBe "ERROR"
    }

    @Test
    fun `mapFailure does not affect Success`() {
        val result = Success("success").mapFailure(String::uppercase)
        result.shouldBeInstanceOf<Success<String>>()
        result.result shouldBe "success"
    }

    @Test
    fun `flatMap transforms Success to new Either`() {
        val result = Success("success").flatMap { Success(it.uppercase()) }
        result.shouldBeInstanceOf<Success<String>>()
        result.result shouldBe "SUCCESS"
    }

    @Test
    fun `flatMap does not affect Failure`() {
        val result = Failure("error").flatMap { Success(Unit) }
        result.shouldBeInstanceOf<Failure<String>>()
        result.cause shouldBe "error"
    }

    @Test
    fun `flatMapFailure transforms Failure to new Either`() {
        val result = Failure("error").flatMapFailure { Success(it.uppercase()) }
        result.shouldBeInstanceOf<Success<String>>()
        result.result shouldBe "ERROR"
    }

    @Test
    fun `flatMapFailure does not affect Success`() {
        val result = Success("success").flatMapFailure { Failure(Unit) }
        result.shouldBeInstanceOf<Success<String>>()
        result.result shouldBe "success"
    }

    @Test(expected = CancellationException::class)
    fun `catch should rethrow CancellationException`() {
        catch { throw CancellationException() }
    }
}
