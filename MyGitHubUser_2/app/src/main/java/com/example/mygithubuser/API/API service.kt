package com.example.mygithubuser.API

import com.example.mygithubuser.Response.SearchResponse
import com.example.mygithubuser.Response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface APIservice {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUser(
        @Query("q")
        query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun detailUser(
        @Path("username")
        username: String
    ): Call<UserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun followersUser(
        @Path("username")
        username: String
    ): Call<ArrayList<UserResponse>>
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun followingUser(
        @Path("username")
        username: String
    ): Call<ArrayList<UserResponse>>



}