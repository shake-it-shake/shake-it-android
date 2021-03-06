package com.semicolon.shakeit.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import com.semicolon.design.color.primary.gray.gray800
import java.io.File

@Composable
fun CircularImage(image: File?, size: Dp, modifier: Modifier = Modifier) {
    Image(
        painter = rememberImagePainter(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(gray800)
    )
}

@Composable
fun CircularImage(imageUrl: String, size: Dp, modifier: Modifier = Modifier) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(gray800)
    )
}