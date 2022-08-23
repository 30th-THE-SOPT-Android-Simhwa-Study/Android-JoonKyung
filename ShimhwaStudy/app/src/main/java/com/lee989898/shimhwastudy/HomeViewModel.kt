package com.lee989898.shimhwastudy

import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private var _userInfo: UserInfo? = null
    val userInfo get() = _userInfo

    fun setUserInfo(userInfo: UserInfo) {
        _userInfo = userInfo
    }
}