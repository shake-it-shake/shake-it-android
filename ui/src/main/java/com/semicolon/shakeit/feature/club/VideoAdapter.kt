package com.semicolon.shakeit.feature.club

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amazonaws.services.chime.sdk.meetings.audiovideo.AttendeeInfo
import com.amazonaws.services.chime.sdk.meetings.audiovideo.AudioVideoFacade
import com.amazonaws.services.chime.sdk.meetings.audiovideo.video.VideoTileState
import com.semicolon.shakeit.R
import com.semicolon.shakeit.databinding.ItemVideoBinding

class VideoAdapter(
    private val onTileClick: (String) -> Unit
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private val videoTiles = mutableListOf<VideoTileState>()
    private var attendees = mutableListOf<AttendeeInfo>()
    private lateinit var audioVideo: AudioVideoFacade

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_video, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoTile = videoTiles[position]
        if (videoTile.isLocalTile) {
            holder.binding.tvNickname.text = "나"
        } else if (attendees.any { it.attendeeId == videoTile.attendeeId }) {
            val externalUserId = attendees
                .first { it.attendeeId == videoTile.attendeeId }
                .externalUserId
                .split("@")
            holder.binding.tvNickname.text = externalUserId.last()
            holder.binding.video.setOnClickListener {
                onTileClick(externalUserId.first())
            }
        }
        audioVideo.bindVideoView(holder.binding.video, videoTiles[position].tileId)
    }

    override fun getItemCount(): Int =
        videoTiles.count()

    fun setAudioVideo(audioVideoFacade: AudioVideoFacade) {
        this.audioVideo = audioVideoFacade
    }

    fun addVideoTile(videoTileState: VideoTileState) {
        videoTiles.add(videoTileState)
        notifyItemInserted(videoTiles.lastIndex)
    }

    fun removeVideoTile(videoTileState: VideoTileState) {
        val removedIndex = videoTiles.indexOf(videoTileState)
        videoTiles.remove(videoTileState)
        notifyItemRemoved(removedIndex)
    }

    fun setAttendees(attendees: Array<AttendeeInfo>) {
        this.attendees = attendees.toMutableList()
    }

    fun addAttendees(attendees: Array<AttendeeInfo>) {
        this.attendees.addAll(attendees)
    }

    fun removeAttendees(attendees: Array<AttendeeInfo>) {
        this.attendees.removeAll(attendees)
    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemVideoBinding = DataBindingUtil.bind(itemView)!!
    }
}