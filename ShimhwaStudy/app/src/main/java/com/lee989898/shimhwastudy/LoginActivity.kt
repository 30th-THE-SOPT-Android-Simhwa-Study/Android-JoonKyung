package com.lee989898.shimhwastudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import com.lee989898.shimhwastudy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@LoginActivity

        initObserves()
    }

    private fun initObserves() {
        viewModel.isCompletedSignIn.observe(this) {
            if (!it) return@observe
            Intent(this, HomeActivity::class.java).run {
                putExtra(USER_INFO, viewModel.userInfo)
                startActivity(this)
                finish()
            }
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
        private const val USER_INFO = "userInfo"
    }
}