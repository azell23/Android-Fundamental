package com.example.mygithubuser.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity(tableName = "favorite")
data class FavoriteEntity(
    var login: String? = null,
    @PrimaryKey
    var id: Int? = 0,
    var avatar: String? = null
): Parcelable
