package com.example.room.di

import com.example.room.data.local.datasource.LocalFriendDataSource
import com.example.room.data.local.datasourceimpl.LocalFriendDataSourceImpl
import com.example.room.db.FriendDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideLocalFriendDataSource(friendDAO: FriendDAO): LocalFriendDataSource =
        LocalFriendDataSourceImpl(friendDAO)

}