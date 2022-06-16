package com.example.room.domain.usecase

import com.example.room.db.Friend
import com.example.room.domain.repository.FriendRepository

class UpdateFriendUseCase(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(friend: Friend): Int = friendRepository.updateFriend(friend)
}