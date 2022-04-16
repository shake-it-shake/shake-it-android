package com.semicolon.shakeit.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.semicolon.design.Button
import com.semicolon.design.button.BasicButton
import com.semicolon.design.button.BasicLargeButton
import com.semicolon.design.color.primary.gray.gray800
import com.semicolon.design.color.primary.gray.gray900

@Composable
fun BigPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    BasicLargeButton(
        text = text,
        defaultColor = Color(0xFFFF6262),
        pressedColor = Color(0xEEFF6262),
        disabledColor = gray900,
        defaultTextColor = Color.White,
        disabledTextColor = gray800,
        isEnabled = isEnabled,
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
fun MediumPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    val textColor = if (isEnabled) Color.White else gray800
    BasicButton(
        defaultColor = Color(0xFFFF6262),
        pressedColor = Color(0xDDFF6262),
        disabledColor = gray900,
        isEnabled = isEnabled,
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp)),
        onClick = onClick
    ) {
        Button(text = text, color = textColor)
    }
}

@Composable
fun MediumDarkButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    val textColor = if (isEnabled) Color.White else gray800
    BasicButton(
        defaultColor = gray800,
        pressedColor = Color(0xDD424242),
        disabledColor = gray800,
        isEnabled = isEnabled,
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp)),
        onClick = onClick
    ) {
        Button(text = text, color = textColor)
    }
}