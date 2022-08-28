package com.lee989898.shimhwastudy.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.models.types.MBTI
import com.lee989898.shimhwastudy.data.models.types.MBTIFeatures
import com.lee989898.shimhwastudy.domain.repositories.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendDetailViewModel @Inject constructor(private val friendRepository: FriendRepository) : ViewModel() {
    private val _friend = MutableLiveData<FriendInfo?>()
    val friend: LiveData<FriendInfo?> get() = _friend

    fun setFriend(friend: FriendInfo) {
        _friend.value = friend
    }

    fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures> = friendRepository.getMBTIFeatures(mbti)

    companion object {
        private const val TAG = "FriendDetailViewModel"
    }
}