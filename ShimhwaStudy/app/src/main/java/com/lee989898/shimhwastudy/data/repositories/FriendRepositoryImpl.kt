package com.lee989898.shimhwastudy.data.repositories

import androidx.lifecycle.LiveData
import com.lee989898.shimhwastudy.data.datasources.FriendLocalDataSource
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.models.types.MBTI
import com.lee989898.shimhwastudy.data.models.types.MBTIFeatures
import com.lee989898.shimhwastudy.domain.repositories.FriendRepository
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(private val friendLocalDataSource: FriendLocalDataSource) :
    FriendRepository {

    override suspend fun insert(friendInfo: FriendInfo) = friendLocalDataSource.insert(friendInfo)
    override suspend fun update(friendInfo: FriendInfo) = friendLocalDataSource.update(friendInfo)
    override suspend fun delete(friendInfo: FriendInfo) = friendLocalDataSource.delete(friendInfo)
    override suspend fun deleteAll() = friendLocalDataSource.deleteAll()
    override fun getAllFriends(): LiveData<List<FriendInfo>> =
        friendLocalDataSource.getAllFriendsInfo()

    override fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures> =
        friendLocalDataSource.getMBTIFeatures(mbti)
}