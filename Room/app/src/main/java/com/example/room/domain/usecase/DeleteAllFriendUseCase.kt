package com.example.room.domain.usecase

import com.example.room.db.Friend
import com.example.room.domain.repository.FriendRepository

class DeleteAllFriendUseCase(
    private val friendRepository: FriendRepository
){
    suspend fun execute(): Int {
        return friendRepository.deleteAll()
    }
}