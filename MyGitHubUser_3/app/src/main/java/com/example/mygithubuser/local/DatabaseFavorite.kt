package com.example.mygithubuser.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class DatabaseFavorite: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: DatabaseFavorite? = null
        @JvmStatic
        fun getInstance(context: Context): DatabaseFavorite{
            if (INSTANCE == null){
                synchronized(DatabaseFavorite::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseFavorite::class.java,"favorite_database")
                        .build()
                }
            }
            return INSTANCE as DatabaseFavorite
        }

    }

}