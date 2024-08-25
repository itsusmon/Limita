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

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.limita.core.domain.model.expense.ExpenseCategory
import app.limita.core.ui.preview.PreviewThemes
import app.limita.core.ui.theme.LimitaTheme


/**
 * A composable function that displays a list item for an expense category.
 *
 * This composable displays the name, total amount, and spent amount of an expense category in a card layout.
 * It also provides a scaling animation when the item is pressed.
 *
 * @param expenseCategory The expense category to display.
 * @param onItemClick A callback function that is invoked when the item is clicked.
 * @param modifier The modifier to be applied to the list item.
 * @param scaleTarget The target scale value when the item is pressed.
 * @param scaleAnimationSpec The animation specification for the scaling effect.
 * @param interactionSource The [MutableInteractionSource] used to track user interactions with the list item.
 */
@Composable
internal fun ExpenseCategoryListItem(
    expenseCategory: ExpenseCategory,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
    checkable: Boolean = false,
    checked: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    scaleTarget: Float = 0.95f,
    scaleAnimationSpec: AnimationSpec<Float> = tween(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleTarget else 1f,
        animationSpec = scaleAnimationSpec,
        label = "expense-category-scale-value",
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onItemClick,
                onClickLabel = expenseCategory.name,
            ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (checkable) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange)
                Spacer(Modifier.width(8.dp))
            }

            Text(
                text = expenseCategory.name,
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = expenseCategory.total.toString(),
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = expenseCategory.spent.toString(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End,
                modifier = Modifier.sizeIn(minWidth = 80.dp),
            )
        }
    }
}

@PreviewThemes
@Composable
private fun ExpenseCategoryListItemPreview() {
    LimitaTheme {
        ExpenseCategoryListItem(
            expenseCategory = ExpenseCategory.exampleExpenseCategories[1],
            onItemClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
