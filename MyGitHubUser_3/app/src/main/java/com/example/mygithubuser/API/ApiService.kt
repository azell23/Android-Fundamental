package com.example.mygithubuser.API

import com.example.mygithubuser.BuildConfig
import com.example.mygithubuser.Response.SearchResponse
import com.example.mygithubuser.Response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface APIservice {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.KEY_API}")
    fun searchUser(
        @Query("q")
        query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.KEY_API}")
    fun detailUser(
        @Path("username")
        username: String
    ): Call<UserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.KEY_API}")
    fun followersUser(
        @Path("username")
        username: String
    ): Call<ArrayList<UserResponse>>
    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.KEY_API}")
    fun followingUser(
        @Path("username")
        username: String
    ): Call<ArrayList<UserResponse>>




}