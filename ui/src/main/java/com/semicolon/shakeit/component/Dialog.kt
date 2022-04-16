package com.semicolon.shakeit.component

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.semicolon.design.Body1
import com.semicolon.design.Body2
import com.semicolon.design.color.primary.gray.gray900

@Composable
fun YesOrNoDialog(
    title: String,
    message: String,
    yes: String = "네",
    no: String = "아니요",
    onAccept: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            Modifier
                .width(240.dp)
                .wrapContentHeight()
                .background(gray900, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Body1(text = title, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.size(16.dp))
            Body2(text = message, color = Color.White)
            Spacer(Modifier.size(28.dp))
            Row {
                MediumPrimaryButton(Modifier.weight(1f), text = yes) { onAccept() }
                Spacer(Modifier.size(16.dp))
                MediumDarkButton(Modifier.weight(1f), text = no) { onDismiss() }
            }
        }
    }
}