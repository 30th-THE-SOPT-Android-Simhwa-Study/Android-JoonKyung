package com.example.room.domain.usecase

import com.example.room.data.local.db.Friend
import com.example.room.domain.repository.FriendRepository

class UpdateFriendUseCase(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(friend: Friend): Int = friendRepository.updateFriend(friend)
}