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

package app.limita.core.common.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named

/**
 * Qualifiers for different coroutine dispatchers.
 */
object Dispatcher {
    /**
     * Qualifier for the default dispatcher, suitable for CPU-intensive tasks.
     */
    val Default: Qualifier = named("coroutine-dispatcher-default")

    /**
     * Qualifier for the IO dispatcher, optimized for input/output operations
     */
    val IO: Qualifier = named("coroutine-dispatcher-io")

    /**
     * Qualifier for the unconfined dispatcher, which doesn't have a specific thread.
     */
    val Unconfined: Qualifier = named("coroutine-dispatcher-unconfined")
}
