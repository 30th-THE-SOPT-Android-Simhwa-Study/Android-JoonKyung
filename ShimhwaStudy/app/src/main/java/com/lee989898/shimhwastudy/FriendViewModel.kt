package com.lee989898.shimhwastudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FriendViewModel(private val friendRepository: FriendRepository) : ViewModel() {

    val friends = friendRepository.friends

    val friendId = MutableLiveData<Int>()
    val inputFriendName = MutableLiveData<String?>()
    val inputFriendEmail = MutableLiveData<String?>()

    val saveUpdateButton = MutableLiveData<String>()
    val clearDeleteButton = MutableLiveData<String>()

    init {
        saveUpdateButton.value = "저장"
        clearDeleteButton.value = "전체삭제"
    }

    fun saveOrUpdate() {
        val name = requireNotNull(inputFriendName.value)
        val email = requireNotNull(inputFriendEmail.value)
        if (saveUpdateButton.value == "저장") {
            insert(FriendInfo(0, name, email))
            initInputValue()
        } else {
            update(FriendInfo(requireNotNull(friendId.value), name, email))
            initInputValue()
            saveUpdateButton.value = "저장"
            clearDeleteButton.value = "전체삭제"
        }
    }

    fun clearAllOrDelete() {
        if (clearDeleteButton.value == "전체삭제") {
            clearAll()
        } else {
            delete(
                FriendInfo(
                    requireNotNull(friendId.value),
                    requireNotNull(inputFriendName.value),
                    requireNotNull(inputFriendEmail.value)
                )
            )
            initInputValue()
            saveUpdateButton.value = "저장"
            clearDeleteButton.value = "전체삭제"
        }
    }

    fun initInputValue() {
        inputFriendName.value = null
        inputFriendEmail.value = null
    }

    private fun insert(friendInfo: FriendInfo) {
        viewModelScope.launch {
            friendRepository.insert(friendInfo)
        }
    }

    private fun update(friendInfo: FriendInfo) {
        viewModelScope.launch {
            friendRepository.update(friendInfo)
        }
    }

    private fun delete(friendInfo: FriendInfo) {
        viewModelScope.launch {
            friendRepository.delete(friendInfo)
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            friendRepository.deleteAll()
        }
    }
}