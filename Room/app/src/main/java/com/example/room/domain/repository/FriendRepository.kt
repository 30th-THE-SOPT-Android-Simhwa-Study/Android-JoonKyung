package com.example.room.domain.repository

import com.example.room.data.models.types.MBTI
import com.example.room.data.models.types.MBTIFeatures
import com.example.room.db.Friend
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    suspend fun insertFriend(friend: Friend): Long
    suspend fun updateFriend(friend: Friend): Int
    suspend fun deleteFriend(friend: Friend): Int
    suspend fun deleteAll(): Int
    fun getAllFriends(): Flow<List<Friend>>
    fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures>
}