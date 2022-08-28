package com.lee989898.shimhwastudy.data.models.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lee989898.shimhwastudy.data.models.types.MBTI
import com.lee989898.shimhwastudy.presentation.views.FriendActivity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = FriendActivity.TABLE_NAME)
data class FriendInfo(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "friend_id")
    val id : Int,

    @ColumnInfo(name = "friend_name")
    val name: String,

    @ColumnInfo(name = "friend_email")
    val email: String,

    @ColumnInfo(name = "friend_mbti")
    val mbti: MBTI?
): Parcelable