package com.example.room.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDAO {

    @Insert
    suspend fun insertFriend(friend: Friend): Long

    @Update
    suspend fun updateFriend(friend: Friend): Int

    @Delete
    suspend fun deleteFriend(friend: Friend): Int

    @Query("DELETE FROM friend_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM friend_data_table")
    fun getAllFriends(): Flow<List<Friend>>

}