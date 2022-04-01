package com.semicolon.domain.entity.friend

import com.semicolon.domain.entity.user.ProfileEntity

data class FriendsEntity(
    val friends: List<ProfileEntity>
)