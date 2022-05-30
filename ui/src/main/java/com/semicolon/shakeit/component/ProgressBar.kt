package com.semicolon.shakeit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.semicolon.design.color.primary.gray.gray800

@Composable
fun ProgressBar(max: Int, current: Int) {
    val percent = current / max.toFloat()
    var barMaxWidth by remember { mutableStateOf(0) }
    val barWidth = LocalDensity.current.run {
        barMaxWidth.toDp().value * if (percent > 1) 1f else if (percent < 0) 0f else percent
    }
    Box(
        Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(gray800, RoundedCornerShape(4.dp))
            .onSizeChanged { barMaxWidth = it.width },
        contentAlignment = Alignment.CenterStart
    ) {
        Spacer(
            modifier = Modifier
                .width(barWidth.dp)
                .height(8
                    .dp)
                .background(Color(0xFFFF6262), RoundedCornerShape(4.dp))
        )
    }
}
