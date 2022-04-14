package com.semicolon.shakeit.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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