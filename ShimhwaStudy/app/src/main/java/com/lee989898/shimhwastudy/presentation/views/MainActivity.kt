package com.lee989898.shimhwastudy.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.shimhwastudy.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}