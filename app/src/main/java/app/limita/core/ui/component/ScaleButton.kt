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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import app.limita.core.ui.preview.PreviewThemes
import app.limita.core.ui.theme.LimitaTheme

/**
 * The ScaleButton component is a customizable button that shrinks when pressed
 * and returns to its original size when released.
 *
 * By default, it shrinks to `90%` of its original size. You can customize the shrink target
 * by providing a different value to the `scaleTarget` parameter.
 * This provides a visual cue to the user that their input is being processed.
 *
 * @param onClick Called when the button is clicked.
 * @param modifier Modifier to be applied to the button.
 * @param scaleTarget The target scale value when the button is pressed. Defaults to 0.9f.
 * Values greater than 1f will cause the button to expand when pressed.
 * @param scaleAnimationSpec The animation spec used to animate the button's scale when pressed.
 * @param enabled Controls the enabled state of the button. When `false`, the button will not
 * be clickable or focusable.
 * @param shape Defines the shape of this button's surface and its shadow.
 * @param colors Button colors.
 * @param elevation Button elevation.
 * @param border Border to draw around the button.
 * @param contentPadding The spacing between the button's edges and its content.
 * @param interactionSource Representing the stream of interaction events from this Button.
 * You can create and pass in your own remembered [MutableInteractionSource] if you want to observe interactions
 * and customize the appearance / behavior of this Button in different interactions.
 * @param content The button's content.
 */
@Composable
fun ScaleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    scaleTarget: Float = 0.9f,
    scaleAnimationSpec: AnimationSpec<Float> = tween(),
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleTarget else 1f,
        animationSpec = scaleAnimationSpec,
        label = "scale-button-scale",
    )

    Button(
        onClick = onClick,
        modifier = modifier.scale(scale),
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

@PreviewThemes
@Composable
private fun ShrinkButtonPreview() {
    LimitaTheme {
        ScaleButton(
            onClick = { },
            modifier = Modifier.padding(4.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier,
            ) {
                Text(text = "Text")
            }
        }
    }
}
