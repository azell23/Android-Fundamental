package com.example.mygithubuser

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygithubuser.API.ApiConfig
import com.example.mygithubuser.Response.UserResponse
import com.example.mygithubuser.local.DatabaseFavorite
import com.example.mygithubuser.local.FavoriteDao
import com.example.mygithubuser.local.FavoriteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val detailUser = MutableLiveData<UserResponse>()
    private val favDao : FavoriteDao

    init {
        val db = DatabaseFavorite.getInstance(application)
        favDao = db.favoriteDao()
    }

    fun addFavorite(id: Int, username: String, avatar: String){
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteEntity(username, id, avatar)
            favDao.addFavorite(user)
        }
    }
    fun getFavorite(): LiveData<List<FavoriteEntity>> {
         return favDao.getFavorite()
    }
    fun delete(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            favDao.delete(id)
        }
    }
    suspend fun check(id: Int) = favDao.check(id)


    fun detailUser(username: String){
        ApiConfig.instance
            .detailUser(username)
            .enqueue(object : Callback<UserResponse> {
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
    fun getDetailUser(): LiveData<UserResponse> {
        return detailUser

    }


}