package com.example.room.domain.usecase

import com.example.room.data.local.db.Friend
import com.example.room.domain.repository.FriendRepository
import kotlinx.coroutines.flow.Flow

class GetAllFriendUseCase(
    private val friendRepository: FriendRepository
) {
    fun execute(): Flow<List<Friend>> = friendRepository.getAllFriends()
}