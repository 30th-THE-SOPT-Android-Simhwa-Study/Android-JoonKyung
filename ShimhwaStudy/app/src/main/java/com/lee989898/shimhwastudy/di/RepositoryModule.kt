package com.lee989898.shimhwastudy.di

import com.lee989898.shimhwastudy.data.repositories.FriendRepositoryImpl
import com.lee989898.shimhwastudy.domain.repositories.FriendRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindFriendRepository(
        friendRepositoryImpl: FriendRepositoryImpl,
    ): FriendRepository
}