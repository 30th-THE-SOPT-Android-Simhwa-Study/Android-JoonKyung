package com.example.room.data.local.datasource

import com.example.room.data.models.types.MBTI
import com.example.room.data.models.types.MBTIFeatures
import com.example.room.data.local.db.Friend
import kotlinx.coroutines.flow.Flow

interface LocalFriendDataSource {
    suspend fun insertFriendToDB(friend: Friend): Long
    suspend fun updateFriendToDB(friend: Friend): Int
    suspend fun deleteFriendFromDB(friend: Friend): Int
    suspend fun deleteAllFromDB(): Int
    fun getAllFriendsFromDB(): Flow<List<Friend>>
    fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures>
}