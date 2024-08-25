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

package app.limita.feature.overview.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import app.limita.feature.overview.api.OverviewFeatureRoute
import app.limita.feature.overview.impl.screen.overview.OverviewScreenRoute
import app.limita.feature.overview.impl.screen.overview.installOverviewScreenRoute

fun NavGraphBuilder.installOverviewFeatureRoute(navController: NavController) {
    navigation<OverviewFeatureRoute>(startDestination = OverviewScreenRoute) {
        installOverviewScreenRoute(navController = navController)
    }
}
