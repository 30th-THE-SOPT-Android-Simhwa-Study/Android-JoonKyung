package com.example.room.di

import com.example.room.data.remote.api.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {

    @Provides
    @Singleton
    fun provideGithubService(
        retrofit: Retrofit
    ): GithubService =
        retrofit.create(GithubService::class.java)
}