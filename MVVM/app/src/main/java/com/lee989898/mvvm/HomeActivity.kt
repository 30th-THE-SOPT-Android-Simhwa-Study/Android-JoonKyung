package com.lee989898.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lee989898.mvvm.databinding.ActivityLoginSuccessBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_success)

//        val getEmail = intent.getParcelableExtra<UserData>("email")
//        binding.email.text = getEmail?.email

        intent.getParcelableExtra<UserData>(EMAIL)?.let { email ->
            binding.email.text = email.email
        }

    }

    companion object {
        private const val EMAIL = "email"
    }
}