package com.example.mygithubuser.Response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("company")
	val company: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("login")
	val login: String

)
data class UserFavorite(
	@field:SerializedName("login")
	var login: String? = null,
	var id: Int? = 0,
	@field:SerializedName("avatar_url")
	var avatarUrl: String? = null
)
