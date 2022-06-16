package com.example.room.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.room.db.FriendDAO
import com.example.room.db.FriendDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideFriendDatabase(@ApplicationContext context: Context): FriendDatabase {
        return Room.databaseBuilder(context, FriendDatabase::class.java, "friend_data_database")
            .build()
    }

    @Provides
    fun provideFriendDao(friendDatabase: FriendDatabase): FriendDAO {
        return friendDatabase.friendDAO
    }
}