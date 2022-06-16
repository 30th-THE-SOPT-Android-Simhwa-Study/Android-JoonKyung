package com.example.room.util

import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.room.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["email", "pwd"], requireAll = true)
    fun setSignInBt(button: Button, email: String?, pwd: String?) {
        if (email.isNullOrBlank() || pwd.isNullOrBlank()) {
            button.setBackgroundResource(R.color.black)
        } else {
            button.setBackgroundResource(R.color.thesopt_purple)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["name", "email", "pwd"], requireAll = true)
    fun setSignUpBt(button: Button, name: String?, email: String?, pwd: String?) {
        if (name.isNullOrBlank() || email.isNullOrBlank() || pwd.isNullOrBlank()) {
            button.setBackgroundResource(R.color.black)
        } else {
            button.setBackgroundResource(R.color.thesopt_purple)
        }
    }

    @JvmStatic
    @BindingAdapter("recyclerGlide")
    fun setImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .into(imageView)
    }
}

