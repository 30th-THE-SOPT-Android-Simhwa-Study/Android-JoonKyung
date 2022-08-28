package com.lee989898.shimhwastudy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FriendActivity.TABLE_NAME)
data class FriendInfo(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "friend_id")
    val id : Int,

    @ColumnInfo(name = "friend_name")
    val name: String,

    @ColumnInfo(name = "friend_email")
    val email: String
)