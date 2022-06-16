package com.example.room.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.room.data.models.types.MBTI
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "friend_data_table")
data class Friend(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "friend_id")
    var id: Int,

    @ColumnInfo(name = "friend_name")
    var name: String,

    @ColumnInfo(name = "friend_email")
    var email: String,

    @ColumnInfo(name = "friend_mbti")
    var mbti: MBTI?
): Parcelable