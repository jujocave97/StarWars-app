package com.example.swapp.dao

import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val dao: CharacterDAO) {
    val allValues: Flow<List<Character>> = dao.getAll()

    fun insert(value: Character)= dao.insert(value)

    fun update(value: Character)= dao.update(value)

    fun getValue(id: Int): Character= dao.getValue(id)

    suspend fun deleteAll()= dao.deleteAll()
}