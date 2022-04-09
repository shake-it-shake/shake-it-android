package com.semicolon.domain.entity.room

data class RoomsEntity(
    val rooms: List<RoomEntity>
) {
    data class RoomEntity(
        val id: Long,
        val title: String,
        val roomImage: String,
        val personnel: Int,
        val currentCount: Int,
        val member: List<MemberEntity>
    )

    data class MemberEntity(
        val name: String,
        val profilePath: String
    )
}
