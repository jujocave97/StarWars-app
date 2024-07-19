package com.example.swapp.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterFavDAO {
    @Query("SELECT * FROM favoritos")
    fun getAll(): Flow<List<CharacterFav>>

    @Query("SELECT * FROM favoritos WHERE characterId= :id")
    fun getValue(id: Int): CharacterFav

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    fun insert(value: CharacterFav)

    @Update
    fun update(value: CharacterFav)

    @Delete
    fun delete(value: CharacterFav)

    @Query("DELETE FROM favoritos")
    suspend fun deleteAll()
}