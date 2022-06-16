package com.example.room.domain.usecase

import com.example.room.data.local.db.Friend
import com.example.room.domain.repository.FriendRepository

class InsertFriendUseCase(private val friendRepository: FriendRepository) {
    suspend fun execute(friend: Friend): Long = friendRepository.insertFriend(friend)
}