package com.lee989898.shimhwastudy.data.models.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.models.db.dao.FriendInfoDao
import com.lee989898.shimhwastudy.presentation.views.FriendActivity

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