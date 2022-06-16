package com.example.room.domain.usecase

import com.example.room.data.local.db.Friend
import com.example.room.domain.repository.FriendRepository

class DeleteFriendUseCase(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(friend: Friend): Int = friendRepository.deleteFriend(friend)
}