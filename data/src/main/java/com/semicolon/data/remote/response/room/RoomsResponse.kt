package com.semicolon.data.remote.response.room

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.room.RoomsEntity
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

data class RoomsResponse(
    @SerializedName("rooms") val rooms: List<Room>
) {
    data class Room(
        @SerializedName("id") val id: Long,
        @SerializedName("title") val title: String,
        @SerializedName("room_image") val roomImage: String,
        @SerializedName("personnel") val personnel: Int,
        @SerializedName("current_count") val currentCount: Int,
        @SerializedName("owner_name") val ownerName: String,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("member") val member: List<Member>
    )

    data class Member(
        @SerializedName("name") val name: String,
        @SerializedName("profile_path") val profilePath: String
    )
}

fun RoomsResponse.toEntity() =
    RoomsEntity(
        rooms = rooms.map { room ->
            RoomsEntity.RoomEntity(
                id = room.id,
                title = room.title,
                roomImage = room.roomImage,
                personnel = room.personnel,
                currentCount = room.currentCount,
                ownerName = room.ownerName,
                createdAt = LocalDateTime.ofInstant(
                    Instant.parse(room.createdAt),
                    ZoneId.systemDefault()
                ),
                member = room.member.map { member ->
                    RoomsEntity.MemberEntity(
                        name = member.name,
                        profilePath = member.profilePath
                    )
                }
            )
        }
    )