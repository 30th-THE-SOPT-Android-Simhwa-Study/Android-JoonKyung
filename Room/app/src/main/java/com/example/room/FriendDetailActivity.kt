package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.room.data.models.types.MBTI
import com.example.room.databinding.ActivityFriendDetailBinding
import com.example.room.db.Friend
import dagger.hilt.android.AndroidEntryPoint
import safeValueOf

@AndroidEntryPoint
class FriendDetailActivity : AppCompatActivity() {

    private val friendDetailViewModel by viewModels<FriendDetailViewModel>()
    private lateinit var binding: ActivityFriendDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_detail)
        binding.viewModel = friendDetailViewModel

        intent.getParcelableExtra<Friend>("friendInfo")?.let {
            friend ->
            friendDetailViewModel.setFriend(friend)
        }
    }


}