package com.example.room

import android.util.Patterns
import androidx.lifecycle.*
import com.example.room.db.Friend
import com.example.room.db.FriendRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FriendViewModel(private val friendRepository: FriendRepository) : ViewModel() {

    fun getSaveFriends() = liveData {
        friendRepository.friends.collect {
            emit(it)
        }
    }

    private var isUpdateOrDelete = false
    private lateinit var friendToUpdateOrDelete: Friend

    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()

    var saveOrUpdateButtonText = MutableLiveData<String>()
    val deleteAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>> get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "저장"
        deleteAllOrDeleteButtonText.value = "모두 삭제"
    }

    fun saveOrUpdate() {

        if (inputName.value == null) {
            statusMessage.value = Event("친구 이름을 입력해주세요.")
        } else if (inputEmail.value == null) {
            statusMessage.value = Event("친구 이메일을 입력해주세요.")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("이메일 형식이 잘못됐습니다.")
        } else {
            if (isUpdateOrDelete) {
                friendToUpdateOrDelete.name = inputName.value!!
                friendToUpdateOrDelete.email = inputEmail.value!!
                updateFriend(friendToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                insertFriend(Friend(0, name, email))
                inputName.value = null
                inputEmail.value = null
            }
        }
    }

    fun deleteAllOrDelete() {
        if (isUpdateOrDelete) {
            deleteFriend(friendToUpdateOrDelete)
        } else {
            deleteAll()
        }
    }

    fun insertFriend(friend: Friend) {
        viewModelScope.launch {
            val newRowId = friendRepository.insertFriend(friend)
            if (newRowId > -1) {
                statusMessage.value = Event("$newRowId 친구 추가 성공 ")
            } else {
                statusMessage.value = Event("오류 발생")
            }
        }
    }

    fun updateFriend(friend: Friend) {
        viewModelScope.launch {
            val noOfRows = friendRepository.updateFriend(friend)
            if (noOfRows > 0) {
                inputName.value = null
                inputEmail.value = null
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "저장"
                deleteAllOrDeleteButtonText.value = "모두 삭제"
                statusMessage.value = Event("$noOfRows 친구 업데이트 성공")
            } else {
                statusMessage.value = Event("오류 발생")
            }

        }
    }

    fun deleteFriend(friend: Friend) {
        viewModelScope.launch {
            val noOfRowsDeleted = friendRepository.deleteFriend(friend)
            if (noOfRowsDeleted > 0) {
                inputName.value = null
                inputEmail.value = null
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "저장"
                deleteAllOrDeleteButtonText.value = "모두 삭제"
                statusMessage.value = Event("$noOfRowsDeleted 친구 삭제 성공")
            } else {
                statusMessage.value = Event("오류 발생")
            }

        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            val noOfRowsDeleted = friendRepository.deleteAll()
            if (noOfRowsDeleted > 0) {
                statusMessage.value = Event("$noOfRowsDeleted 친구 모두 삭제 성공")
            } else {
                statusMessage.value = Event("오류 발생")
            }
        }
    }

    fun initUpdateAndDelete(friend: Friend) {
        inputName.value = friend.name
        inputEmail.value = friend.email
        isUpdateOrDelete = true
        friendToUpdateOrDelete = friend
        saveOrUpdateButtonText.value = "업데이트"
        deleteAllOrDeleteButtonText.value = "삭제"
    }

}