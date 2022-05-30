package com.semicolon.shakeit.feature.clubs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.semicolon.design.Body2
import com.semicolon.design.Body3
import com.semicolon.design.color.primary.gray.gray900
import com.semicolon.domain.entity.room.RoomsEntity
import com.semicolon.shakeit.component.CircularImage
import com.semicolon.shakeit.component.MediumDarkButton
import com.semicolon.shakeit.component.MediumPrimaryButton
import com.semicolon.shakeit.component.ProgressBar
import com.semicolon.shakeit.util.fetchTimeGap

@Composable
fun ClubItem(
    room: RoomsEntity.RoomEntity,
    onJoinClick: (RoomsEntity.RoomEntity) -> Unit
) {
    var isOpened by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(12.dp), color = gray900)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { isOpened = true }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularImage(imageUrl = room.roomImage, size = 40.dp)
            Spacer(Modifier.size(16.dp))
            Column {
                Body2(text = room.title, color = Color.White, fontWeight = FontWeight.Medium)
                Row {
                    val timeGep = fetchTimeGap(room.createdAt).toString() + "분 전 오픈"
                    Body3(
                        text = room.ownerName,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.size(8.dp))
                    Body3(text = timeGep, color = Color.White)
                }
            }
        }
        Spacer(Modifier.size(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val peoples = room.member
                .filterIndexed { index, _ -> index <= 2 }
                .joinToString(", ") { it.name }
            val joinedMessage =
                if (room.member.size > 3) " 외 ${room.member.size - 3}명 참여중"
                else " 참여중"
            Box {
                room.member.forEachIndexed { index, memberEntity ->
                    val paddingStart = 10 * index
                    if (index <= 2) CircularImage(
                        imageUrl = memberEntity.profilePath,
                        size = 20.dp,
                        modifier = Modifier.padding(start = paddingStart.dp)
                    )
                }
            }
            Spacer(Modifier.size(8.dp))
            Body3(
                text = peoples,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.widthIn(max = 172.dp)
            )
            Body3(text = joinedMessage, color = Color.White)
        }
        Spacer(Modifier.size(12.dp))
        ProgressBar(max = room.personnel, current = room.currentCount)
        Spacer(Modifier.size(4.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Body3(text = "현재 ${room.currentCount}명 참여중", color = Color.White)
            Body3(text = "정원 ${room.personnel}명", color = Color.White)
        }
        if (isOpened) {
            Spacer(Modifier.size(12.dp))
            Row {
                MediumPrimaryButton(Modifier.weight(1f), text = "입장") { onJoinClick(room) }
                Spacer(Modifier.size(16.dp))
                MediumDarkButton(Modifier.weight(1f), text = "닫기") { isOpened = false }
            }
        }
    }
}