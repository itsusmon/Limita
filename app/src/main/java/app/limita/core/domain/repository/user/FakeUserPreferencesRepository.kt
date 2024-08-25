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

package app.limita.core.domain.repository.user

import java.util.concurrent.atomic.AtomicBoolean

class FakeUserPreferencesRepository : UserPreferencesRepository {

    private var isOnboardingCompleted = AtomicBoolean()

    override suspend fun isOnboardingCompleted(): Boolean = isOnboardingCompleted.get()

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        isOnboardingCompleted.set(completed)
    }
}
