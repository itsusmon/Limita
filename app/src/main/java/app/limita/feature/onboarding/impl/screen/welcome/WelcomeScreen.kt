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

package app.limita.feature.onboarding.impl.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.limita.android.R
import app.limita.core.ui.component.ScaleButton
import app.limita.core.ui.component.ThemeTransitionScope
import app.limita.core.ui.preview.PreviewDarkTheme
import app.limita.core.ui.theme.LimitaTheme
import app.limita.core.ui.theme.asBoolean
import app.limita.feature.onboarding.impl.screen.welcome.component.ThemeSwitcher
import app.limita.feature.onboarding.impl.screen.welcome.state.WelcomeAction
import app.limita.feature.onboarding.impl.screen.welcome.state.WelcomeAction.CompleteOnboarding
import app.limita.feature.onboarding.impl.screen.welcome.state.WelcomeAction.ToggleDarkMode
import app.limita.feature.onboarding.impl.screen.welcome.state.WelcomeAction.ToggleLightMode
import app.limita.feature.onboarding.impl.screen.welcome.state.WelcomeViewState

@Composable
internal fun WelcomeScreen(
    state: WelcomeViewState,
    onAction: (WelcomeAction) -> Unit,
) {
    val isDarkMode = state.darkMode.asBoolean()

    ThemeTransitionScope(
        toggleTheme = {
            onAction(if (isDarkMode) ToggleLightMode else ToggleDarkMode)
        },
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                var switchCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }
                ThemeSwitcher(
                    darkMode = isDarkMode,
                    onClick = {
                        val offset = switchCoordinates?.boundsInWindow()?.center
                            ?: return@ThemeSwitcher
                        startThemeTransition(offset)
                    },
                    modifier = Modifier
                        .padding(28.dp)
                        .size(28.dp)
                        .align(Alignment.TopEnd)
                        .onGloballyPositioned { coordinates ->
                            switchCoordinates = coordinates
                        },
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape),
                    )
                    Text(
                        text = stringResource(R.string.onboarding_title),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp),
                    )

                    Text(
                        text = stringResource(R.string.onboarding_message),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp),
                    )

                    ScaleButton(
                        onClick = { onAction(CompleteOnboarding) },
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(text = stringResource(R.string.onboarding_complete))
                    }
                }
            }
        }
    }
}


@PreviewDarkTheme
@Composable
private fun WelcomeScreenPreview() {
    val state = WelcomeViewState()
    LimitaTheme {
        WelcomeScreen(
            state = state,
            onAction = {},
        )
    }
}
