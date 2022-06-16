package com.example.room.presentation.frienddetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.room.R
import com.example.room.databinding.ActivityFriendDetailBinding
import com.example.room.data.local.db.Friend
import com.example.room.presentation.frienddetail.viewmodel.FriendDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

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