package com.semicolon.domain.entity.room

import com.amazonaws.services.chime.sdk.meetings.session.Attendee
import com.amazonaws.services.chime.sdk.meetings.session.Meeting

data class ClubEntity(
    val meeting: Meeting,
    val attendee: Attendee?
)