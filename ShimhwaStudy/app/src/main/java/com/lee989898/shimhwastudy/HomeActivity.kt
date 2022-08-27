package com.lee989898.shimhwastudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lee989898.shimhwastudy.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.homeViewModel = viewModel
        binding.lifecycleOwner = this@HomeActivity

        intent.getParcelableExtra<UserInfo>(USER_INFO)?.let {
            viewModel.setUserInfo(it)
        }

        initListener()
    }

    private fun initListener() {
        binding.btFriendList.setOnClickListener {
            startActivity(Intent(this, FriendActivity::class.java))
        }
    }

    companion object {
        private const val TAG = "HomeActivity"
        private const val USER_INFO = "userInfo"
    }
}