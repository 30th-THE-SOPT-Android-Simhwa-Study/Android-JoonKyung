package com.example.helpme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helpme.databinding.ActivityRecommendBinding

class RecommendActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecommendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStore.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

}