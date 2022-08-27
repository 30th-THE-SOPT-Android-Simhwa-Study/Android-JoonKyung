package com.lee989898.shimhwastudy

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FriendInfoDao {

    @Insert
    suspend fun insertFriendInfo(friendInfo: FriendInfo) : Long

    @Update
    suspend fun updateFriendInfo(friendInfo: FriendInfo): Int

    @Delete
    suspend fun deleteFriendInfo(friendInfo: FriendInfo): Int

    @Query("DELETE FROM " + FriendActivity.TABLE_NAME)
    suspend fun deleteAllFriends(): Int

    @Query("SELECT * FROM " + FriendActivity.TABLE_NAME + " ORDER BY friend_name ASC")
    fun getAllFriends(): LiveData<List<FriendInfo>>
}