package com.example.room.di

import com.example.room.data.local.datasource.LocalFriendDataSource
import com.example.room.data.local.datasourceimpl.LocalFriendDataSourceImpl
import com.example.room.data.local.db.FriendDAO
import com.example.room.data.remote.api.GithubService
import com.example.room.data.remote.datasource.GithubDataSource
import com.example.room.data.remote.datasourceimpl.GithubDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideGithubDataSource(githubService: GithubService): GithubDataSource =
        GithubDataSourceImpl(githubService)
}