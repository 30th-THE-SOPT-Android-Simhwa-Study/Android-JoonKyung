package com.lee989898.mvvm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val email: String,
    val pwd: String
): Parcelable
