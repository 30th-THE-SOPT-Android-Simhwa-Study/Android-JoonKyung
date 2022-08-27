package com.lee989898.shimhwastudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lee989898.shimhwastudy.databinding.ActivityFriendBinding

class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding
    private lateinit var friendListAdapter: FriendListAdapter
    private val friendViewModel: FriendViewModel by lazy {
        val friendDao = FriendDatabase.getInstance(application).friendInfoDao
        val friendRepository = FriendRepository(friendDao)
        val friendFactory = FriendViewModelFactory(friendRepository)
        ViewModelProvider(this, friendFactory)[FriendViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend)
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
        friendViewModel.statusMessage.observe(this) {
            Toast.makeText(this, it.getContentIfNotHandled(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun adapterClickListener(id: Int, name: String, email: String) {
        with(friendViewModel) {
            friendId.value = id
            inputFriendName.value = name
            inputFriendEmail.value = email
            isUpdateOrDelete = true
            saveUpdateButton.value = "업데이트"
            clearDeleteButton.value = "삭제"
        }
    }

    companion object {
        private const val TAG = "FriendActivity"
        const val TABLE_NAME = "friend_info_table"
    }
}