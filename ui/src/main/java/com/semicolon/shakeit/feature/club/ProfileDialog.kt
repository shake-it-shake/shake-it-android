package com.semicolon.shakeit.feature.club

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.semicolon.domain.entity.user.ProfileEntity
import com.semicolon.shakeit.R
import com.semicolon.shakeit.databinding.DialogProfileBinding
import com.semicolon.shakeit.util.makeToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileDialog(
    private val context: Context,
    private val friendRequestViewModel: FriendRequestViewModel,
    private val profileEntity: ProfileEntity
) {

    private lateinit var binding: DialogProfileBinding

    fun createDialog() {
        val dialog = Dialog(context)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_profile,
            null,
            false
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(binding.root)

        Glide.with(context)
            .load(profileEntity.imagePath)
            .into(binding.ivProfile)
        binding.tvNickname.text = profileEntity.nickname
        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnFriendRequest.setOnClickListener {
            sendFriendRequest()
        }
        CoroutineScope(Dispatchers.IO).launch {
            friendRequestViewModel.eventFlow.collect {
                when (it) {
                    is FriendRequestViewModel.Event.Success -> {
                        completeFriendRequest()
                    }
                    is FriendRequestViewModel.Event.Failure -> {
                        enableFriendRequestButton()
                        makeToast(context, "친구 요청을 보내지 못했습니다")
                    }
                }
            }
        }
        dialog.show()
    }

    private fun sendFriendRequest() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.btnFriendRequest.isEnabled = false
            binding.btnFriendRequest.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(0x42, 0x42, 0x42))
            binding.btnFriendRequest.setTextColor(Color.rgb(0x61, 0x61, 0x61))
        }
        friendRequestViewModel.addFriend(profileEntity.id)
    }

    private fun enableFriendRequestButton() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.btnFriendRequest.isEnabled = true
            binding.btnFriendRequest.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(0xFF, 0x62, 0x62))
            binding.btnFriendRequest.setTextColor(Color.rgb(0xFF, 0xFF, 0xFF))
        }
    }

    private fun completeFriendRequest() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.btnFriendRequest.text = "신청 완료"
        }
    }
}