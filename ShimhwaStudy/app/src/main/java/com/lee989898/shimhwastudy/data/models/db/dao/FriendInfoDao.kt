package com.lee989898.shimhwastudy.data.models.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.presentation.views.FriendActivity

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