package com.lee989898.shimhwastudy.domain.repositories

import androidx.lifecycle.LiveData
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.models.types.MBTI
import com.lee989898.shimhwastudy.data.models.types.MBTIFeatures

interface FriendRepository {

    suspend fun insert(friendInfo: FriendInfo): Long
    suspend fun update(friendInfo: FriendInfo): Int
    suspend fun delete(friendInfo: FriendInfo): Int
    suspend fun deleteAll(): Int
    fun getAllFriends(): LiveData<List<FriendInfo>>
    fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures>
}