package com.lee989898.shimhwastudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lee989898.shimhwastudy.databinding.ActivityFriendBinding

class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding
    private lateinit var friendViewModel: FriendViewModel
    private lateinit var friendListAdapter: FriendListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend)
        val friendDao = FriendDatabase.getInstance(application).friendInfoDao
        val friendRepository = FriendRepository(friendDao)
        val friendFactory = FriendViewModelFactory(friendRepository)
        friendViewModel = ViewModelProvider(this, friendFactory)[FriendViewModel::class.java]

        binding.friendViewModel = friendViewModel
        binding.lifecycleOwner = this@FriendActivity

        initObserve()
        initAdapter()
    }

    private fun initAdapter() {
        friendListAdapter = FriendListAdapter(::adapterClickListener)

        binding.rvFriend.adapter = friendListAdapter
    }

    private fun initObserve() {
        friendViewModel.friends.observe(this) {
            friendListAdapter.submitList(it)
        }
    }

    private fun adapterClickListener(id: Int, name: String, email: String) {
        friendViewModel.friendId.value = id
        friendViewModel.inputFriendName.value = name
        friendViewModel.inputFriendEmail.value = email
        friendViewModel.saveUpdateButton.value = "업데이트"
        friendViewModel.clearDeleteButton.value = "삭제"
    }

    companion object {
        private const val TAG = "FriendActivity"
        const val TABLE_NAME = "friend_info_table"
    }
}