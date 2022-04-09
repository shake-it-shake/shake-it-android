package com.semicolon.data.remote.response.room

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.room.RoomsEntity

data class RoomsResponse(
    @SerializedName("rooms") val rooms: List<Room>
) {
    data class Room(
        @SerializedName("id") val id: Long,
        @SerializedName("title") val title: String,
        @SerializedName("room_image") val roomImage: String,
        @SerializedName("personnel") val personnel: Int,
        @SerializedName("current_count") val currentCount: Int,
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
                member = room.member.map { member ->
                    RoomsEntity.MemberEntity(
                        name = member.name,
                        profilePath = member.profilePath
                    )
                }
            )
        }
    )