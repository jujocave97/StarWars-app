package com.example.swapp.favorites

import kotlinx.coroutines.flow.Flow

class CharacterFavRepository(private val dao: CharacterFavDAO){
    val allValues: Flow<List<CharacterFav>> = dao.getAll()

    fun insert(value: CharacterFav)= dao.insert(value)

    fun update(value: CharacterFav)= dao.update(value)

    fun getValue(id: Int): CharacterFav= dao.getValue(id)

    suspend fun deleteAll()= dao.deleteAll()
}