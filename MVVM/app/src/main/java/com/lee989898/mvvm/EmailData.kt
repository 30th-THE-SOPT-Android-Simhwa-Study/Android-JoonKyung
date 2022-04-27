package com.lee989898.mvvm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class EmailData(
    val email: String
): Parcelable
