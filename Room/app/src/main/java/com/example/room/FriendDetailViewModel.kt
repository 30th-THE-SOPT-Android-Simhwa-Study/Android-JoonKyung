package com.example.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.room.data.models.types.MBTI
import com.example.room.data.models.types.MBTIFeatures
import com.example.room.db.Friend
import com.example.room.domain.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendDetailViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    private val friend = MutableLiveData<Friend?>()

    fun setFriend(friend: Friend) {
        this.friend.value = friend
    }

    fun getFriend(): LiveData<Friend?> = friend

    fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures> {
        return friendRepository.getMBTIFeatures(mbti)
    }

    companion object {
        private const val TAG = "FriendDetailViewModel"
    }
}
