package com.example.room.domain.usecase

import com.example.room.db.Friend
import com.example.room.domain.repository.FriendRepository

class DeleteFriendUseCase(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(friend: Friend): Int = friendRepository.deleteFriend(friend)
}