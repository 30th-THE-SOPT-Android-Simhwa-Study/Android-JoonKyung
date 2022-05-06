package com.example.room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_data_table")
data class Friend(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "friend_id")
    var id: Int,

    @ColumnInfo(name = "friend_name")
    var name: String,

    @ColumnInfo(name = "friend_email")
    var email: String
)