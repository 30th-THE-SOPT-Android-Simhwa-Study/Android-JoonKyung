package com.lee989898.shimhwastudy.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lee989898.shimhwastudy.R
import com.lee989898.shimhwastudy.data.datasources.FriendLocalDataSource
import com.lee989898.shimhwastudy.data.models.db.database.FriendDatabase
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.repositories.FriendRepositoryImpl
import com.lee989898.shimhwastudy.databinding.ActivityFriendDetailBinding
import com.lee989898.shimhwastudy.presentation.viewmodels.FriendDetailViewModel
import com.lee989898.shimhwastudy.presentation.viewmodels.FriendDetailViewModelFactory

class FriendDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendDetailBinding
    private val friendDetailViewModel: FriendDetailViewModel by lazy {
        val friendDao = FriendDatabase.getInstance(application).friendInfoDao
        val friendLocalDataSource = FriendLocalDataSource(friendDao)
        val friendRepository = FriendRepositoryImpl(friendLocalDataSource)
        val friendFactory = FriendDetailViewModelFactory(friendRepository)
        ViewModelProvider(this, friendFactory)[FriendDetailViewModel::class.java]
    }

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