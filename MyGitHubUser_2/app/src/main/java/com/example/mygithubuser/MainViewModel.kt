package com.example.mygithubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuser.API.APIconfig
import com.example.mygithubuser.Response.SearchResponse
import com.example.mygithubuser.Response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val searchUser = MutableLiveData<ArrayList<UserResponse>>()
    private val detailUser = MutableLiveData<UserResponse>()
    private val followUser = MutableLiveData<ArrayList<UserResponse>>()


    fun searchUser(query: String){
        APIconfig.instance
            .searchUser(query)
            .enqueue(object : retrofit2.Callback<SearchResponse>{
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

    fun detailUser(username: String){
        APIconfig.instance
            .detailUser(username)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        detailUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("failure",t.message.toString())
                }

            })
        
    }
    fun getDetailUser():LiveData<UserResponse>{
        return detailUser
        
    }
    fun followersUser(username: String){
        APIconfig.instance
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
        APIconfig.instance
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