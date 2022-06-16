package com.example.room.domain.usecase

import com.example.room.data.models.types.MBTI
import com.example.room.data.models.types.MBTIFeatures
import com.example.room.domain.repository.FriendRepository

class GetMBTIFeaturesUseCase(
    private val friendRepository: FriendRepository
) {
    fun execute(mbti: MBTI): List<MBTIFeatures> = friendRepository.getMBTIFeatures(mbti)
}