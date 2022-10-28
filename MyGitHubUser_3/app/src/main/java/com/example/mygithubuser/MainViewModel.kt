package com.example.mygithubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuser.API.ApiConfig
import com.example.mygithubuser.Response.SearchResponse
import com.example.mygithubuser.Response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val searchUser = MutableLiveData<ArrayList<UserResponse>>()
    private val followUser = MutableLiveData<ArrayList<UserResponse>>()


    fun searchUser(query: String){
        ApiConfig.instance
            .searchUser(query)
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful){
                        searchUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.d("failure" , t.message.toString())
                }

            })
    }
    fun getSearchUser():LiveData<ArrayList<UserResponse>>{
        return searchUser
    }

    fun followersUser(username: String){
        ApiConfig.instance
            .followersUser(username)
            .enqueue(object : Callback<ArrayList<UserResponse>>{

                override fun onResponse(
                    call: Call<ArrayList<UserResponse>>,
                    response: Response<ArrayList<UserResponse>>
                ) {
                    if (response.isSuccessful){
                        followUser.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable){
                    Log.d("failure",t.message.toString())
                }
            })
    }
    fun getFollowers():LiveData<ArrayList<UserResponse>>{
        return followUser
    }
    fun followingUser(username: String){
        ApiConfig.instance
            .followingUser(username)
            .enqueue(object : Callback<ArrayList<UserResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<UserResponse>>,
                    response: Response<ArrayList<UserResponse>>
                ) {
                    if (response.isSuccessful){
                        followUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {
                    Log.d("failure", t.message.toString())
                }

            })
    }
    fun getFollowing():LiveData<ArrayList<UserResponse>>{
        return followUser
    }
}

