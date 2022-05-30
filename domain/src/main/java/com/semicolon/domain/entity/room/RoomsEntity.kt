package com.semicolon.domain.entity.room

import org.threeten.bp.LocalDateTime

data class RoomsEntity(
    val rooms: List<RoomEntity>
) {
    data class RoomEntity(
        val id: Long,
        val title: String,
        val roomImage: String,
        val personnel: Int,
        val currentCount: Int,
        val ownerName: String,
        val createdAt: LocalDateTime,
        val member: List<MemberEntity>
    )

    data class MemberEntity(
        val name: String,
        val profilePath: String
    )
}
