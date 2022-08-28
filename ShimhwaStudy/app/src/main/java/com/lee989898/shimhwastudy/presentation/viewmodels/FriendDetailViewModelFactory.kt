package com.lee989898.shimhwastudy.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lee989898.shimhwastudy.domain.repositories.FriendRepository

class FriendDetailViewModelFactory(private val friendRepository: FriendRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendDetailViewModel::class.java)) {
            return FriendDetailViewModel(friendRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}