package com.example.mygithubuser.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIconfig {

    companion object{

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val instance = retrofit.create(APIservice::class.java)


    }
}