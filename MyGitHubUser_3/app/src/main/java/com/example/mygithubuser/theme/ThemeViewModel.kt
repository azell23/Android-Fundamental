package com.example.mygithubuser.theme

import android.app.Application
import androidx.lifecycle.*
import com.example.mygithubuser.datastore.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThemeViewModel(private val pref: Preferences) : ViewModel() {
    fun getTheme():LiveData<Boolean> = pref.getTheme().asLiveData(Dispatchers.IO)

    fun saveTheme(isDarkActive:Boolean){
        viewModelScope.launch {
            pref.saveTheme(isDarkActive)
        }
    }
    class Factory(private val pref: Preferences):ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ThemeViewModel::class.java))
                return ThemeViewModel(pref) as T
            throw IllegalArgumentException("unknown ViewModel Class:" +modelClass.name)
        }
    }
}
