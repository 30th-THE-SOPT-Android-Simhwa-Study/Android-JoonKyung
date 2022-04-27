package com.lee989898.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.lee989898.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.loginModel = viewModel
        binding.lifecycleOwner = this


        viewModel.getLoginSuccess().observe(this, Observer<Boolean> { result ->
            if (result == true) {
                viewModel.setEmail(binding.email.text.toString())
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", UserData(viewModel.getEmail().value.toString()))
                startActivity(intent)
            }
        })



        binding.button.setOnClickListener {
            viewModel.loginSuccess()
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }

        viewModel.getLoginButton().observe(this, Observer {

        })

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.emailCheck(binding.email.text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }
        )

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.pwdCheck(binding.password.text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


    }
}