package com.example.mygithubuser.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addFavorite(favoriteEntity: FavoriteEntity)
    @Query("SELECT Count(*) FROM favorite WHERE favorite.id =  :id")
     fun check(id : Int): Int
    @Query("SELECT * FROM favorite")
    fun getFavorite(): LiveData<List<FavoriteEntity>>
    @Query("DELETE FROM favorite WHERE favorite.id = :id")
     fun delete(id: Int): Int
}