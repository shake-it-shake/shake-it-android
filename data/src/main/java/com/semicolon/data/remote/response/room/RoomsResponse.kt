package com.semicolon.data.remote.response.room

import com.google.gson.annotations.SerializedName

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
