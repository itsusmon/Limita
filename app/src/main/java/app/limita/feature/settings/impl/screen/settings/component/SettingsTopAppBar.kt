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

package app.limita.feature.settings.impl.screen.settings.component

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
import app.limita.feature.settings.impl.screen.settings.state.SettingsAction

@Composable
internal fun SettingsTopAppBar(
    onAction: (SettingsAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { onAction(SettingsAction.NavigateBack) },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                    contentDescription = stringResource(R.string.navigate_back),
                )
            }
        },
        title = {
            Text(text = stringResource(R.string.feature_settings_title))
        },
        actions = {
            IconButton(
                onClick = { onAction(SettingsAction.NavigateToAbout) },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_info),
                    contentDescription = stringResource(R.string.feature_about_title),
                )
            }
        },
        modifier = modifier.fillMaxWidth(),
    )
}

@PreviewDarkTheme
@Composable
private fun SettingsTopAppBarPreview() {
    LimitaTheme {
        SettingsTopAppBar(
            onAction = {},
        )
    }
}
