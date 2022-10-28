package com.example.mygithubuser.API

import com.example.mygithubuser.Response.SearchResponse
import com.example.mygithubuser.Response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface APIservice {

    @GET("search/users")
    @Headers("Authorization: token (YOURTOKEN)")
    fun searchUser(
        @Query("q")
        query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token (YOURTOKEN)")
    fun detailUser(
        @Path("username")
        username: String
    ): Call<UserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token (YOURTOKEN)")
    fun followersUser(
        @Path("username")
        username: String
    ): Call<ArrayList<UserResponse>>
    @GET("users/{username}/following")
    @Headers("Authorization: token (YOURTOKEN)")
    fun followingUser(
        @Path("username")
        username: String
    ): Call<ArrayList<UserResponse>>



}
