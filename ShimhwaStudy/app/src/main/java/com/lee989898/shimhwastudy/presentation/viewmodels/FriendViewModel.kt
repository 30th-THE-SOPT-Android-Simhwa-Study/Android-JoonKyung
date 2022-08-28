package com.lee989898.shimhwastudy.presentation.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.models.types.MBTI
import com.lee989898.shimhwastudy.domain.repositories.FriendRepository
import com.lee989898.shimhwastudy.utils.Event
import com.lee989898.shimhwastudy.utils.safeValueOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(private val friendRepository: FriendRepository) : ViewModel() {


    val friends = friendRepository.getAllFriends()

    var isUpdateOrDelete = false

    val friendId = MutableLiveData<Int>()
    val inputFriendName = MutableLiveData<String?>()
    val inputFriendEmail = MutableLiveData<String?>()
    val inputFriendMBTI = MutableLiveData<String?>()

    val saveUpdateButton = MutableLiveData("저장")
    val clearDeleteButton = MutableLiveData("전체삭제")

    private val _statusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>> get() = _statusMessage

    fun saveOrUpdate() {
        val name = inputFriendName.value
        val email = inputFriendEmail.value
        val mbti = inputFriendMBTI.value
        if (name.isNullOrBlank()) {
            _statusMessage.value = Event("친구 이름을 입력해주세요.")
        } else if (email.isNullOrBlank()) {
            _statusMessage.value = Event("친구 이메일을 입력해주세요.")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _statusMessage.value = Event("이메일 형식이 잘못됐습니다.")
        } else if (isUpdateOrDelete) {
            update(
                FriendInfo(
                    requireNotNull(friendId.value),
                    name,
                    email,
                    safeValueOf<MBTI>(mbti?.uppercase())
                )
            )
            initInputValue()
            saveUpdateButton.value = "저장"
            clearDeleteButton.value = "전체삭제"
        } else {
            insert(FriendInfo(0, name, email, safeValueOf<MBTI>(mbti?.uppercase())))
            initInputValue()
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(
                FriendInfo(
                    requireNotNull(friendId.value),
                    requireNotNull(inputFriendName.value),
                    requireNotNull(inputFriendEmail.value),
                    requireNotNull(safeValueOf<MBTI>(inputFriendMBTI.value?.uppercase()))
                )
            )
            initInputValue()
            saveUpdateButton.value = "저장"
            clearDeleteButton.value = "전체삭제"
        } else {
            clearAll()
        }
    }

    private fun initInputValue() {
        inputFriendName.value = null
        inputFriendEmail.value = null
        inputFriendMBTI.value = null
        isUpdateOrDelete = false
    }

    private fun insert(friendInfo: FriendInfo) {
        viewModelScope.launch {
            val noOfFriendInsert = friendRepository.insert(friendInfo)
            if (noOfFriendInsert > -1) {
                _statusMessage.value = Event("$noOfFriendInsert 번째 친구 추가 성공")
            } else {
                _statusMessage.value = Event("오류 발생")
            }
        }
    }

    private fun update(friendInfo: FriendInfo) {
        viewModelScope.launch {
            val noOfFriendUpdated = friendRepository.update(friendInfo)
            if (noOfFriendUpdated > 0) {
                _statusMessage.value = Event("$noOfFriendUpdated 명 업데이트 성공")
            } else {
                _statusMessage.value = Event("오류 발생")
            }
        }
    }

    private fun delete(friendInfo: FriendInfo) {
        viewModelScope.launch {
            val noOfFriendDeleted = friendRepository.delete(friendInfo)
            if (noOfFriendDeleted > 0) {
                _statusMessage.value = Event("$noOfFriendDeleted 친구 삭제 성공")
            } else {
                _statusMessage.value = Event("오류 발생")
            }
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            val noOfFriendsDeleted = friendRepository.deleteAll()
            if (noOfFriendsDeleted > 0) {
                _statusMessage.value = Event("$noOfFriendsDeleted 명 친구 모두 삭제 성공")
            } else {
                _statusMessage.value = Event("오류 발생")
            }
        }
    }
}