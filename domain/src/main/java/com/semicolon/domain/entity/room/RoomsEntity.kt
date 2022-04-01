package com.semicolon.domain.entity.room

data class RoomsEntity(
    val rooms: List<Room>
) {
    data class Room(
        val id: Long,
        val title: String,
        val roomImage: String,
        val personnel: Int,
        val currentCount: Int,
        val member: List<Member>
    )

    data class Member(
        val name: String,
        val profilePath: String
    )
}
