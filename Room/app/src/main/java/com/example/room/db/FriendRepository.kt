package com.example.room.db

class FriendRepository(private val friendDao: FriendDAO) {

    val friends = friendDao.getAllFriends()

    suspend fun insertFriend(friend: Friend): Long{
        return friendDao.insertFriend(friend)
    }

    suspend fun updateFriend(friend: Friend): Int{
        return friendDao.updateFriend(friend)
    }

    suspend fun deleteFriend(friend: Friend): Int{
        return friendDao.deleteFriend(friend)
    }

    suspend fun deleteAll(): Int{
        return friendDao.deleteAll()
    }

}