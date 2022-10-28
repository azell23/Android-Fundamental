package com.example.mygithubuser.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class Preferences constructor(private val dataStore: DataStore<Preferences>) {

    private val key = booleanPreferencesKey("settings_theme")

    fun getTheme(): Flow<Boolean>{
        return dataStore.data.map {
            preference -> preference[key] ?: false
        }
    }

    suspend fun saveTheme(isDarkModeActive: Boolean){
        dataStore.edit{ preference ->
            preference[key] = isDarkModeActive
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: com.example.mygithubuser.datastore.Preferences? = null

        fun getInstance(dataStore: DataStore<Preferences>):com.example.mygithubuser.datastore.Preferences{
            return INSTANCE?: synchronized(this){
                val instance = Preferences(dataStore)
                INSTANCE = instance
                instance
            }
        }

    }


}