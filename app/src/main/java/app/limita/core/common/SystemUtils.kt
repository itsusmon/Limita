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

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.ChecksSdkIntAtLeast

/**
 * Provides utility methods related to the system environment.
 */
object SystemUtils {

    /**
     * Determines whether the device's operating system supports dynamic color features.
     *
     * @return `true` if dynamic color features are supported, `false` otherwise.
     */
    inline val isDynamicColorSupported: Boolean
        @ChecksSdkIntAtLeast(VERSION_CODES.S)
        get() = VERSION.SDK_INT >= VERSION_CODES.S

    /**
     * Determines whether the current application is running in debug mode.
     *
     * @return `true` if the application is running in debug mode, `false` otherwise.
     */
    inline val Context.isDebuggableApp: Boolean
        get() = applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
}
