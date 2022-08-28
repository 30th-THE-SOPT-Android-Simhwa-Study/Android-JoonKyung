package com.lee989898.shimhwastudy.data.datasources

import com.lee989898.shimhwastudy.data.models.db.dao.FriendInfoDao
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.models.types.MBTI
import com.lee989898.shimhwastudy.data.models.types.MBTIFeatures
import javax.inject.Inject

class FriendLocalDataSource @Inject constructor(private val friendInfoDao: FriendInfoDao) {

    suspend fun insert(friendInfo: FriendInfo) = friendInfoDao.insertFriendInfo(friendInfo)
    suspend fun update(friendInfo: FriendInfo) = friendInfoDao.updateFriendInfo(friendInfo)
    suspend fun delete(friendInfo: FriendInfo) = friendInfoDao.deleteFriendInfo(friendInfo)
    suspend fun deleteAll() = friendInfoDao.deleteAllFriends()
    fun getAllFriendsInfo() = friendInfoDao.getAllFriends()
    fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures> = when (mbti) {
        MBTI.ISTJ -> listOf(MBTIFeatures.ISTJ1, MBTIFeatures.ISTJ2, MBTIFeatures.ISTJ3)
        MBTI.ISTP -> listOf(MBTIFeatures.ISTP1, MBTIFeatures.ISTP2, MBTIFeatures.ISTP3)
        MBTI.ISFJ -> listOf(MBTIFeatures.ISFJ1, MBTIFeatures.ISFJ2, MBTIFeatures.ISFJ3)
        MBTI.ISFP -> listOf(MBTIFeatures.ISFP1, MBTIFeatures.ISFP2, MBTIFeatures.ISFP3)

        MBTI.INTJ -> listOf(MBTIFeatures.INTJ1, MBTIFeatures.INTJ2, MBTIFeatures.INTJ3)
        MBTI.INTP -> listOf(MBTIFeatures.INTP1, MBTIFeatures.INTP2, MBTIFeatures.INTP3)
        MBTI.INFJ -> listOf(MBTIFeatures.INFJ1, MBTIFeatures.INFJ2, MBTIFeatures.INFJ3)
        MBTI.INFP -> listOf(MBTIFeatures.INFP1, MBTIFeatures.INFP2, MBTIFeatures.INFP3)

        MBTI.ESTJ -> listOf(MBTIFeatures.ESTJ1, MBTIFeatures.ESTJ2, MBTIFeatures.ESTJ3)
        MBTI.ESTP -> listOf(MBTIFeatures.ESTP1, MBTIFeatures.ESTP2, MBTIFeatures.ESTP3)
        MBTI.ESFJ -> listOf(MBTIFeatures.ESFJ1, MBTIFeatures.ESFJ2, MBTIFeatures.ESFJ3)
        MBTI.ESFP -> listOf(MBTIFeatures.ESFP1, MBTIFeatures.ESFP2, MBTIFeatures.ESFP3)

        MBTI.ENTJ -> listOf(MBTIFeatures.ENTJ1, MBTIFeatures.ENTJ2, MBTIFeatures.ENTJ3)
        MBTI.ENFJ -> listOf(MBTIFeatures.ENFJ1, MBTIFeatures.ENFJ2, MBTIFeatures.ENFJ3)
        MBTI.ENTP -> listOf(MBTIFeatures.ENTP1, MBTIFeatures.ENTP2, MBTIFeatures.ENTP3)
        else -> listOf(MBTIFeatures.ENFP1, MBTIFeatures.ENFP2, MBTIFeatures.ENFP3)
    }
}