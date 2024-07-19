package com.example.swapp.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM character ORDER BY id")
    fun getAll(): Flow<List<Character>>

    @Query("SELECT * FROM character WHERE id= :id")
    fun getValue(id: Int): Character

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    fun insert(value: Character)

    @Update
    fun update(value: Character)

    @Delete
    fun delete(value: Character)

    @Query("DELETE FROM character")
    suspend fun deleteAll()
}