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

package app.limita.feature.overview.impl.screen.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.limita.core.domain.model.expense.ExpenseCategory
import app.limita.core.ui.component.ExpenseCategoryListItem
import app.limita.core.ui.preview.PreviewDarkTheme
import app.limita.core.ui.theme.LimitaTheme
import app.limita.feature.overview.impl.screen.overview.component.OverviewTopAppBar
import app.limita.feature.overview.impl.screen.overview.state.OverviewAction
import app.limita.feature.overview.impl.screen.overview.state.OverviewViewState

@Composable
internal fun OverviewScreen(
    state: OverviewViewState,
    onAction: (OverviewAction) -> Unit,
) {
    Scaffold(
        topBar = { OverviewTopAppBar(onAction = onAction) },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            items(
                items = state.expenseCategories,
                key = ExpenseCategory::id,
            ) { expenseCategory ->
                ExpenseCategoryListItem(
                    expenseCategory = expenseCategory,
                    onItemClick = {

                    },
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                )
            }
        }
    }
}

@PreviewDarkTheme
@Composable
private fun OverviewScreenPreview() {
    LimitaTheme {
        OverviewScreen(
            state = OverviewViewState(expenseCategories = ExpenseCategory.exampleExpenseCategories),
            onAction = {},
        )
    }
}
