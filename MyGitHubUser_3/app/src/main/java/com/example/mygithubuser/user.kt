package com.example.mygithubuser

import android.os.Parcelable


@kotlinx.parcelize.Parcelize
data class user(
    var username : String,
    var location : String,
    var name : String,
    var company : String,
    var repository : String,
    var follower : String,
    var following : String,
    var photo : Int
): Parcelable
