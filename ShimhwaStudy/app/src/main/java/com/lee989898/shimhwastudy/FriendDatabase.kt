package com.lee989898.shimhwastudy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FriendInfo::class], version = 1)
abstract class FriendDatabase : RoomDatabase() {

    abstract val friendInfoDao: FriendInfoDao

    companion object {
        @Volatile
        private var INSTANCE: FriendDatabase? = null
        fun getInstance(context: Context): FriendDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FriendDatabase::class.java,
                        FriendActivity.TABLE_NAME
                    ).build()
                }
                return instance
            }
        }
    }
}