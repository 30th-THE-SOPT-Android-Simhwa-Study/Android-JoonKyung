package com.lee989898.shimhwastudy.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.lee989898.shimhwastudy.R
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.databinding.ActivityFriendDetailBinding
import com.lee989898.shimhwastudy.presentation.viewmodels.FriendDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendDetailBinding
    private val friendDetailViewModel: FriendDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_detail)
        binding.friendDetailViewModel = friendDetailViewModel
        binding.lifecycleOwner = this@FriendDetailActivity

        intent.getParcelableExtra<FriendInfo>("friendInfo")?.let {
            friendDetailViewModel.setFriend(it)
        }
    }
}