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

package app.limita.android

import android.app.Application
import app.limita.core.common.SystemUtils.isDebuggableApp
import app.limita.feature.about.impl.di.AboutFeatureModule
import app.limita.feature.onboarding.impl.di.OnboardingFeatureModule
import app.limita.feature.overview.impl.di.OverviewFeatureModule
import app.limita.feature.settings.impl.di.SettingsFeatureModule
import app.limita.feature.statistics.impl.di.StatisticsFeatureModule
import logcat.AndroidLogcatLogger
import logcat.LogcatLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val MainModule = module {
    viewModelOf(::MainViewModel)
}

class LimitaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (isDebuggableApp) LogcatLogger.install(AndroidLogcatLogger())
        startKoin {
            androidContext(applicationContext)
            if (isDebuggableApp) androidLogger(level = Level.DEBUG)
            modules(
                MainModule,
                OnboardingFeatureModule,
                OverviewFeatureModule,
                StatisticsFeatureModule,
                SettingsFeatureModule,
                AboutFeatureModule,
            )
        }
    }
}
