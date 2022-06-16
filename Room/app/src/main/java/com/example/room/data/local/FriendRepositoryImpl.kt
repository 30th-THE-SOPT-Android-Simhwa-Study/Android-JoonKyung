package com.example.room.data.local

import com.example.room.data.local.datasource.LocalFriendDataSource
import com.example.room.data.models.types.MBTI
import com.example.room.data.models.types.MBTIFeatures
import com.example.room.data.local.db.Friend
import com.example.room.domain.repository.FriendRepository
import kotlinx.coroutines.flow.Flow

class FriendRepositoryImpl(
    private val friendLocalFriendDataSource: LocalFriendDataSource
) : FriendRepository {

    override suspend fun insertFriend(friend: Friend): Long {
        return friendLocalFriendDataSource.insertFriendToDB(friend)
    }

    override suspend fun updateFriend(friend: Friend): Int {
        return friendLocalFriendDataSource.updateFriendToDB(friend)
    }

    override suspend fun deleteFriend(friend: Friend): Int {
        return friendLocalFriendDataSource.deleteFriendFromDB(friend)
    }

    override suspend fun deleteAll(): Int {
        return friendLocalFriendDataSource.deleteAllFromDB()
    }

    override fun getAllFriends(): Flow<List<Friend>> {
        return friendLocalFriendDataSource.getAllFriendsFromDB()
    }

    override fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures> {
        return friendLocalFriendDataSource.getMBTIFeatures(mbti)
    }
}