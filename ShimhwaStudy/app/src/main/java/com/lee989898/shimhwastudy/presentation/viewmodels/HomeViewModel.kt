package com.lee989898.shimhwastudy.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.lee989898.shimhwastudy.domain.models.UserInfo

class HomeViewModel: ViewModel() {

    private var _userInfo: UserInfo? = null
    val userInfo get() = _userInfo

    fun setUserInfo(userInfo: UserInfo) {
        _userInfo = userInfo
    }
}