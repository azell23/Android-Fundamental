package com.example.mygithubuser.Response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("total_count")
	val totalCount: String,

	@field:SerializedName("incomplete_results")
	val incompleteResults: String,

	@field:SerializedName("items")
	val items: ArrayList<UserResponse>
)
