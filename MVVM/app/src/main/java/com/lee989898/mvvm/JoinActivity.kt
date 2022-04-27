package com.lee989898.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lee989898.mvvm.databinding.ActivityJoinBinding
import com.lee989898.mvvm.databinding.ActivityMainBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        binding.button.setOnClickListener {
            finish()
        }

    }
}