package com.lee989898.shimhwastudy.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val email: String,
    val password: String,
) : Parcelable
