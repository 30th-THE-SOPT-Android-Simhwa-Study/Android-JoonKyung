package com.example.room.di

import com.example.room.domain.repository.FriendRepository
import com.example.room.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideDeleteAllFriendUseCase(
        friendRepository: FriendRepository
    ): DeleteAllFriendUseCase {
        return DeleteAllFriendUseCase(friendRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteFriendUseCase(
        friendRepository: FriendRepository
    ): DeleteFriendUseCase {
        return DeleteFriendUseCase(friendRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllFriendUseCase(
        friendRepository: FriendRepository
    ): GetAllFriendUseCase {
        return GetAllFriendUseCase(friendRepository)
    }

    @Singleton
    @Provides
    fun provideGetMBTIUseCase(
        friendRepository: FriendRepository
    ): GetMBTIFeaturesUseCase {
        return GetMBTIFeaturesUseCase(friendRepository)
    }

    @Singleton
    @Provides
    fun provideInsertFriendUseCase(
        friendRepository: FriendRepository
    ): InsertFriendUseCase {
        return InsertFriendUseCase(friendRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateFriendUseCase(
        friendRepository: FriendRepository
    ): UpdateFriendUseCase {
        return UpdateFriendUseCase(friendRepository)
    }

}