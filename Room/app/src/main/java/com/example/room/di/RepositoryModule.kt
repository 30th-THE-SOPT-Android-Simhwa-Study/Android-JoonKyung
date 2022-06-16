package com.example.room.di

import com.example.room.data.local.FriendRepositoryImpl
import com.example.room.data.local.datasource.LocalFriendDataSource
import com.example.room.domain.repository.FriendRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideFriendRepository(friendDataSource: LocalFriendDataSource): FriendRepository =
        FriendRepositoryImpl(friendDataSource)
}