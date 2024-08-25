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

package app.limita.core.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.limita.core.domain.model.expense.Expense
import app.limita.core.ui.preview.PreviewThemes
import app.limita.core.ui.theme.LimitaTheme
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format

private val ExpenseItemTimeFormatter = LocalTime.Formats.ISO

@Composable
internal fun ExpenseListItem(
    expense: Expense,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    checkable: Boolean = false,
    checked: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (checkable) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange)
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = expense.amount.toString(),
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = expense.expenseDate.time.format(ExpenseItemTimeFormatter),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}


@PreviewThemes
@Composable
private fun ExpenseListItemPreview() {
    val expense = Expense.exampleExpenses.random()
    LimitaTheme {
        ExpenseListItem(
            expense = expense,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
