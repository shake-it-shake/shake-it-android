package com.semicolon.shakeit.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.semicolon.design.Body3
import com.semicolon.design.color.primary.gray.gray500
import com.semicolon.design.color.primary.gray.gray800

@Composable
fun TextField(
    text: String,
    isSecret: Boolean = false,
    onTextChange: (String) -> Unit,
    placeholder: String = ""
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        decorationBox = {
            Box(contentAlignment = Alignment.CenterStart) {
                if (text.isEmpty()) Body3(placeholder, color = gray500)
                it()
            }
        },
        textStyle = TextStyle(color = Color.White),
        singleLine = true,
        maxLines = 1,
        cursorBrush = SolidColor(Color(0xFFFF6262)),
        visualTransformation = if(isSecret) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(width = 1.dp, color = gray800, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp)
    )
}