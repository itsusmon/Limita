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

package app.limita.core.ui.state

/**
 * Represents the state of a view in the MVI architecture.
 *
 * @param T The type of data associated with the [Success] state.
 */
sealed interface ViewState<out T> {

    val data: T?

    /**
     * Represents the loading state.
     */
    @JvmInline
    value class Loading<out T>(override val data: T?) : ViewState<T>

    /**
     * Represents the success state with the associated data.
     *
     * @property data The data associated with the success state.
     */
    @JvmInline
    value class Success<out T>(override val data: T) : ViewState<T>

    /**
     * Represents the error state with the associated throwable.
     *
     * @property cause The throwable associated with the error state.
     */
    data class Failure<out T>(
        val cause: Throwable,
        override val data: T? = null,
    ) : ViewState<T>

    companion object {
        /**
         * Represents the loading state with no associated data.
         */
        val Loading = Loading<Nothing>(data = null)
    }
}
