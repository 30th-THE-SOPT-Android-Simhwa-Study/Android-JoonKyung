package com.lee989898.shimhwastudy.di

import android.content.Context
import com.lee989898.shimhwastudy.data.models.db.dao.FriendInfoDao
import com.lee989898.shimhwastudy.data.models.db.database.FriendDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): FriendInfoDao =
        FriendDatabase.getInstance(context).friendInfoDao
}