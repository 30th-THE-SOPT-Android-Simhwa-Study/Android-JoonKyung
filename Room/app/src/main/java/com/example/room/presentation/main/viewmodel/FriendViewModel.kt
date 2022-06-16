package com.example.room.presentation.main.viewmodel

import android.util.Patterns
import androidx.lifecycle.*
import com.example.room.data.models.types.MBTI
import com.example.room.data.local.db.Friend
import com.example.room.domain.repository.FriendRepository
import com.example.room.domain.usecase.*
import com.example.room.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import safeValueOf
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val insertFriendUseCase: InsertFriendUseCase,
    private val updateFriendUseCase: UpdateFriendUseCase,
    private val deleteFriendUseCase: DeleteFriendUseCase,
    private val getAllFriendUseCase: GetAllFriendUseCase,
    private val deleteAllFriendUseCase: DeleteAllFriendUseCase
) :
    ViewModel() {

    fun getSaveFriends() = liveData {
        getAllFriendUseCase.execute().collect {
            emit(it)
        }
    }

    private var isUpdateOrDelete = false
    private lateinit var friendToUpdateOrDelete: Friend

    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()
    var inputMBTI = MutableLiveData<String?>()

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
        } else if (inputMBTI.value == null) {
            statusMessage.value = Event("친구 MBTI를 입력해주세요.")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("이메일 형식이 잘못됐습니다.")
        } else {
            if (isUpdateOrDelete) {
                friendToUpdateOrDelete.name = inputName.value!!
                friendToUpdateOrDelete.email = inputEmail.value!!
                friendToUpdateOrDelete.mbti = safeValueOf<MBTI>(inputMBTI.value?.uppercase())
                updateFriend(friendToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                val mbti = inputMBTI.value!!
                insertFriend(
                    Friend(
                        0,
                        name,
                        email,
                        safeValueOf<MBTI>(inputMBTI.value?.uppercase())
                    )
                )
                inputName.value = null
                inputEmail.value = null
                inputMBTI.value = null
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
            val newRowId = insertFriendUseCase.execute(friend)
            if (newRowId > -1) {
                statusMessage.value = Event("$newRowId 친구 추가 성공 ")
            } else {
                statusMessage.value = Event("오류 발생")
            }
        }
    }

    fun updateFriend(friend: Friend) {
        viewModelScope.launch {
            val noOfRows = updateFriendUseCase.execute(friend)
            if (noOfRows > 0) {
                inputName.value = null
                inputEmail.value = null
                inputMBTI.value = null
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
            val noOfRowsDeleted = deleteFriendUseCase.execute(friend)
            if (noOfRowsDeleted > 0) {
                inputName.value = null
                inputEmail.value = null
                inputMBTI.value = null
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
            val noOfRowsDeleted = deleteAllFriendUseCase.execute()
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
        inputMBTI.value = safeValueOf<MBTI>(inputMBTI.value?.uppercase()).toString()
        isUpdateOrDelete = true
        friendToUpdateOrDelete = friend
        saveOrUpdateButtonText.value = "업데이트"
        deleteAllOrDeleteButtonText.value = "삭제"
    }
}