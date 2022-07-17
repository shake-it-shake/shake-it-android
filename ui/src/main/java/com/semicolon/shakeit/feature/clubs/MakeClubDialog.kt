package com.semicolon.shakeit.feature.clubs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.semicolon.design.Body1
import com.semicolon.design.color.primary.gray.gray900
import com.semicolon.shakeit.component.CircularImage
import com.semicolon.shakeit.component.MediumDarkButton
import com.semicolon.shakeit.component.MediumPrimaryButton
import com.semicolon.shakeit.component.TextField
import com.semicolon.shakeit.util.fetchImage
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun MakeClubDialog(
    onCreate: (File, String, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var profileImage by remember { mutableStateOf<File?>(null) }
    var clubName by remember { mutableStateOf("") }
    var maxPeople by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val isEnabled = (profileImage != null)
            && (clubName.isNotEmpty())
            && (maxPeople.isNotEmpty())
            && (maxPeople.length <= 2)
            && (maxPeople.toInt() > 1)
            && (maxPeople.toInt() <= 12)
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            Modifier
                .width(240.dp)
                .height(388.dp)
                .background(gray900, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Body1(text = "클럽 생성", color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.size(16.dp))
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularImage(
                    image = profileImage,
                    size = 128.dp,
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            fetchImage(context)?.let { profileImage = it }
                        }
                    })
            }
            Spacer(Modifier.size(16.dp))
            TextField(
                text = clubName,
                onTextChange = { clubName = it },
                placeholder = "클럽 이름"
            )
            Spacer(Modifier.size(12.dp))
            TextField(
                text = maxPeople,
                onTextChange = { maxPeople = it },
                placeholder = "최대 인원",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(Modifier.size(16.dp))
            Row {
                MediumPrimaryButton(
                    Modifier.weight(1f),
                    text = "생성",
                    isEnabled = isEnabled
                ) { onCreate(profileImage!!, clubName, maxPeople.toInt()) }
                Spacer(Modifier.size(16.dp))
                MediumDarkButton(Modifier.weight(1f), text = "취소") { onDismiss() }
            }
        }
    }

}
