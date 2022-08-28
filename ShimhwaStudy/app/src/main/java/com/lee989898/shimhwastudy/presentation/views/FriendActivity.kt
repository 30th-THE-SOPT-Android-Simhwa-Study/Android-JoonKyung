package com.lee989898.shimhwastudy.presentation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.lee989898.shimhwastudy.R
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.models.types.MBTI
import com.lee989898.shimhwastudy.databinding.ActivityFriendBinding
import com.lee989898.shimhwastudy.presentation.adapters.FriendListAdapter
import com.lee989898.shimhwastudy.presentation.viewmodels.FriendViewModel
import com.lee989898.shimhwastudy.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding
    private lateinit var friendListAdapter: FriendListAdapter
    private val friendViewModel: FriendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend)
        binding.friendViewModel = friendViewModel
        binding.lifecycleOwner = this@FriendActivity

        initObserve()
        initAdapter()
    }

    private fun initAdapter() {
        friendListAdapter = FriendListAdapter(::updateDeleteClickListener, ::showFriendDetail)
        binding.rvFriend.adapter = friendListAdapter
    }

    private fun initObserve() {
        friendViewModel.friends.observe(this) {
            friendListAdapter.submitList(it)
        }
        friendViewModel.statusMessage.observe(this) {
            showToast(it.getContentIfNotHandled().toString())
        }
    }

    private fun updateDeleteClickListener(id: Int, name: String, email: String, mbti: MBTI?) {
        with(friendViewModel) {
            friendId.value = id
            inputFriendName.value = name
            inputFriendEmail.value = email
            inputFriendMBTI.value = mbti.toString()
            isUpdateOrDelete = true
            saveUpdateButton.value = "업데이트"
            clearDeleteButton.value = "삭제"
        }
    }

    private fun showFriendDetail(friendInfo: FriendInfo?) {
        if (friendInfo?.mbti == null) {
            showToast("MBTI 정보가 없습니다.")
        } else {
            val intent = Intent(this, FriendDetailActivity::class.java)
            intent.putExtra("friendInfo", friendInfo)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "FriendActivity"
        const val TABLE_NAME = "friend_info_table"
    }
}