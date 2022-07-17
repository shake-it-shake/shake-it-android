package com.semicolon.data.remote.response.room

import com.amazonaws.services.chime.sdk.meetings.session.Attendee
import com.amazonaws.services.chime.sdk.meetings.session.Meeting
import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.room.ClubEntity

data class ClubResponse(
    @SerializedName("Meeting") val meetingResponse: Meeting,
    @SerializedName("Attendance") val attendeeResponse: Attendee?
)

fun ClubResponse.toEntity() = ClubEntity(
    meeting = meetingResponse,
    attendee = attendeeResponse
)