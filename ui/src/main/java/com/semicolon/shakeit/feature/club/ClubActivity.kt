package com.semicolon.shakeit.feature.club

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.amazonaws.services.chime.sdk.meetings.audiovideo.*
import com.amazonaws.services.chime.sdk.meetings.audiovideo.video.RemoteVideoSource
import com.amazonaws.services.chime.sdk.meetings.audiovideo.video.VideoTileObserver
import com.amazonaws.services.chime.sdk.meetings.audiovideo.video.VideoTileState
import com.amazonaws.services.chime.sdk.meetings.realtime.RealtimeObserver
import com.amazonaws.services.chime.sdk.meetings.session.*
import com.amazonaws.services.chime.sdk.meetings.utils.logger.ConsoleLogger
import com.amazonaws.services.chime.sdk.meetings.utils.logger.LogLevel
import com.semicolon.domain.entity.room.ClubEntity
import com.semicolon.shakeit.R
import com.semicolon.shakeit.databinding.ActivityClubBinding
import com.semicolon.shakeit.feature.club.ClubActivity.IntentKey.ROOM_ID
import com.semicolon.shakeit.feature.club.ClubActivity.IntentKey.ROOM_IMAGE_PATH
import com.semicolon.shakeit.feature.club.ClubActivity.IntentKey.ROOM_PERSONNEL
import com.semicolon.shakeit.feature.club.ClubActivity.IntentKey.ROOM_TITLE
import com.semicolon.shakeit.util.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

@AndroidEntryPoint
class ClubActivity : AppCompatActivity() {

    lateinit var binding: ActivityClubBinding
    lateinit var audioVideo: AudioVideoFacade

    private val viewModel: ClubViewModel by viewModels()
    private val friendRequestViewModel: FriendRequestViewModel by viewModels()
    private val videoAdapter = VideoAdapter {
        viewModel.fetchProfile(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_club)
        val roomId = intent.getStringExtra(ROOM_ID)
        val roomTitle = intent.getStringExtra(ROOM_TITLE)
        val roomImagePath = intent.getStringExtra(ROOM_IMAGE_PATH)
        val roomPersonnel = intent.getIntExtra(ROOM_PERSONNEL, 0)
        binding.rvVideos.adapter = videoAdapter
        binding.tvTitle.text = roomTitle
        binding.btnLeave.setOnClickListener { finish() }
        lifecycleScope.launch {
            viewModel.eventFlow.collect {
                when (it) {
                    is ClubViewModel.Event.JoinRoomEvent.Success ->
                        setChime(it.clubEntity)
                    is ClubViewModel.Event.JoinRoomEvent.Failure ->
                        finish()
                    is ClubViewModel.Event.CreateRoom.Success ->
                        viewModel.joinRoom(it.clubEntity.meeting.MeetingId)
                    is ClubViewModel.Event.CreateRoom.Failure ->
                        finish()
                    is ClubViewModel.Event.FetchProfile.Success ->
                        ProfileDialog(
                            this@ClubActivity,
                            friendRequestViewModel,
                            it.profileEntity
                        ).createDialog()
                    is ClubViewModel.Event.FetchProfile.Failure ->
                        makeToast(this@ClubActivity, "프로필 정보를 가져오지 못했습니다")
                }
            }
        }
        if (roomId != null)
            viewModel.joinRoom(roomId)
        else if (roomTitle != null && roomImagePath != null && roomPersonnel != 0)
            viewModel.createRoom(File(roomImagePath), roomTitle, roomPersonnel)
    }

    override fun onDestroy() {
        runBlocking { viewModel.leaveRoom() }
        if (this::audioVideo.isInitialized) {
            audioVideo.stopLocalVideo()
            audioVideo.stopRemoteVideo()
            audioVideo.stop()
        }
        super.onDestroy()
    }

    private fun setChime(room: ClubEntity) {
        val meetingSession = DefaultMeetingSession(
            MeetingSessionConfiguration(
                CreateMeetingResponse(room.meeting),
                CreateAttendeeResponse(room.attendee!!)
            ),
            ConsoleLogger(),
            this
        )
        audioVideo = meetingSession.audioVideo
        videoAdapter.setAudioVideo(audioVideo)
        audioVideo.addAudioVideoObserver(object : AudioVideoObserver {
            override fun onAudioSessionCancelledReconnect() {}
            override fun onAudioSessionDropped() {}
            override fun onAudioSessionStarted(reconnecting: Boolean) {}
            override fun onAudioSessionStartedConnecting(reconnecting: Boolean) {}
            override fun onAudioSessionStopped(sessionStatus: MeetingSessionStatus) {}
            override fun onConnectionBecamePoor() {}
            override fun onConnectionRecovered() {}
            override fun onRemoteVideoSourceAvailable(sources: List<RemoteVideoSource>) {}
            override fun onRemoteVideoSourceUnavailable(sources: List<RemoteVideoSource>) {}
            override fun onVideoSessionStarted(sessionStatus: MeetingSessionStatus) {
                audioVideo.startLocalVideo()
                audioVideo.startRemoteVideo()
            }

            override fun onVideoSessionStartedConnecting() {}
            override fun onVideoSessionStopped(sessionStatus: MeetingSessionStatus) {}
        })
        audioVideo.addVideoTileObserver(object : VideoTileObserver {
            override fun onVideoTileAdded(tileState: VideoTileState) {
                println("asdf " + tileState.attendeeId)
                videoAdapter.addVideoTile(tileState)
            }

            override fun onVideoTileRemoved(tileState: VideoTileState) {
                audioVideo.unbindVideoView(tileState.tileId)
                videoAdapter.removeVideoTile(tileState)
            }

            override fun onVideoTilePaused(tileState: VideoTileState) {}
            override fun onVideoTileResumed(tileState: VideoTileState) {}
            override fun onVideoTileSizeChanged(tileState: VideoTileState) {}
        })
        audioVideo.addRealtimeObserver(object : RealtimeObserver {
            override fun onAttendeesDropped(attendeeInfo: Array<AttendeeInfo>) {
                println("asdf " + attendeeInfo.toList())
                videoAdapter.setAttendees(attendeeInfo)
            }

            override fun onAttendeesJoined(attendeeInfo: Array<AttendeeInfo>) {
                println("asdf " + attendeeInfo.toList())
                videoAdapter.addAttendees(attendeeInfo)
            }

            override fun onAttendeesLeft(attendeeInfo: Array<AttendeeInfo>) {
                println("asdf " + attendeeInfo.toList())
                videoAdapter.removeAttendees(attendeeInfo)
            }

            override fun onAttendeesMuted(attendeeInfo: Array<AttendeeInfo>) {}
            override fun onAttendeesUnmuted(attendeeInfo: Array<AttendeeInfo>) {}
            override fun onSignalStrengthChanged(signalUpdates: Array<SignalUpdate>) {}
            override fun onVolumeChanged(volumeUpdates: Array<VolumeUpdate>) {}

        })
        audioVideo.start()
    }

    object IntentKey {
        const val ROOM_ID = "KEY_ROOM_ID"
        const val ROOM_TITLE = "KEY_ROOM_TITLE"
        const val ROOM_IMAGE_PATH = "KEY_ROOM_IMAGE"
        const val ROOM_PERSONNEL = "KEY_ROOM_PERSONNEL"
    }
}