package com.lee989898.shimhwastudy

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("android:invisibility")
fun View.setInvisibility(isInvisible: Boolean) {
    this.isInvisible = isInvisible
}