package com.semicolon.shakeit.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semicolon.design.Button
import com.semicolon.design.color.primary.gray.gray900

@Composable
fun Tab(
    tabItems: List<TabItem>,
    selectedIndex: Int = 0,
) {
    if (tabItems.isEmpty()) return
    var selected by remember { mutableStateOf(tabItems[selectedIndex].label) }
    Column {
        Row(Modifier.fillMaxWidth()) {
            tabItems.forEach {
                TabItem(
                    modifier = Modifier.weight(1f),
                    isSelected = it.label == selected,
                    label = it.label
                ) {
                    selected = it.label
                }
            }
        }
        tabItems.first {it.label == selected}.content()
    }
}

@Composable
fun TabItem(
    modifier: Modifier,
    isSelected: Boolean,
    label: String,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val indicatorColor by animateColorAsState(if (isSelected) Color(0xFFFF6262) else gray900)
    Box(
        modifier
            .height(52.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(text = label, color = Color.White)
        }
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(indicatorColor)
        )
    }
}

data class TabItem(
    val label: String,
    val content: @Composable () -> Unit
)

@Preview(showBackground = true)
@Composable
fun TabPreview() {
    Tab(
        tabItems = listOf(
            TabItem("클럽") {},
            TabItem("참가자") {}
        )
    )
}