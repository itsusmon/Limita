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

package app.limita.core.common.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Creates a flow that emits a single value returned by the [block] function.
 *
 * This is a convenience function for creating a simple flow that executes a suspending function
 * and emits its result as a single element.
 *
 * @param block a suspending function that produces a value to be emitted.
 * @return a flow that emits the result of [block] and then completes.
 */
inline fun <T> flowOf(crossinline block: suspend () -> T): Flow<T> = flow {
    val value = block()
    emit(value)
}
