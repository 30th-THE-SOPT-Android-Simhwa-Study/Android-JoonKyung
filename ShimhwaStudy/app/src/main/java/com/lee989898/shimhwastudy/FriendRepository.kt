package com.lee989898.shimhwastudy

class FriendRepository(private val friendInfoDao: FriendInfoDao) {

    val friends = friendInfoDao.getAllFriends()

    suspend fun insert(friendInfo: FriendInfo) = friendInfoDao.insertFriendInfo(friendInfo)
    suspend fun update(friendInfo: FriendInfo) = friendInfoDao.updateFriendInfo(friendInfo)
    suspend fun delete(friendInfo: FriendInfo) = friendInfoDao.deleteFriendInfo(friendInfo)
    suspend fun deleteAll() = friendInfoDao.deleteAllFriends()
}