package com.example.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room.db.FriendRepository
import java.lang.IllegalArgumentException

class FriendViewModelFactory(private val friendRepository: FriendRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java)){
            return FriendViewModel(friendRepository) as T
        }
        throw IllegalArgumentException("알 수 없는 뷰모델 클래스")
    }

}