package com.example.room.data.local.datasourceimpl

import com.example.room.data.local.datasource.LocalFriendDataSource
import com.example.room.data.models.types.MBTI
import com.example.room.data.models.types.MBTIFeatures
import com.example.room.data.local.db.Friend
import com.example.room.data.local.db.FriendDAO
import kotlinx.coroutines.flow.Flow

class LocalFriendDataSourceImpl(
    private val friendDAO: FriendDAO
) : LocalFriendDataSource {
    override suspend fun insertFriendToDB(friend: Friend): Long = friendDAO.insertFriend(friend)
    override suspend fun updateFriendToDB(friend: Friend): Int = friendDAO.updateFriend(friend)
    override suspend fun deleteFriendFromDB(friend: Friend): Int = friendDAO.deleteFriend(friend)
    override suspend fun deleteAllFromDB(): Int = friendDAO.deleteAll()
    override fun getAllFriendsFromDB(): Flow<List<Friend>> = friendDAO.getAllFriends()
    override fun getMBTIFeatures(mbti: MBTI?): List<MBTIFeatures> {
        return when (mbti) {
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
}