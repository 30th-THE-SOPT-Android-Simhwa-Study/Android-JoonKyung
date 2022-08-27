package com.lee989898.shimhwastudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FriendViewModelFactory(private val friendRepository: FriendRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java)) {
            return FriendViewModel(friendRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}