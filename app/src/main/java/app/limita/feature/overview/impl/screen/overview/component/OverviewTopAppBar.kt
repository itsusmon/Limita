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

package app.limita.feature.overview.impl.screen.overview.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import app.limita.android.R
import app.limita.core.ui.preview.PreviewDarkTheme
import app.limita.core.ui.theme.LimitaTheme
import app.limita.feature.overview.impl.screen.overview.state.OverviewAction

@Composable
internal fun OverviewTopAppBar(
    onAction: (OverviewAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        actions = {
            IconButton(
                onClick = { onAction(OverviewAction.NavigateToStatistics) },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_chart_line),
                    contentDescription = stringResource(R.string.feature_statistics_title),
                )
            }

            IconButton(
                onClick = { onAction(OverviewAction.NavigateToSettings) },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_settings),
                    contentDescription = stringResource(R.string.feature_settings_title),
                )
            }
        },
        modifier = modifier.fillMaxWidth(),
    )
}


@PreviewDarkTheme
@Composable
private fun OverviewTopAppBarPreview() {
    LimitaTheme {
        OverviewTopAppBar(
            onAction = {},
        )
    }
}
