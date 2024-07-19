package com.example.swapp.dao


import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swapp.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class CharacterViewModel( application: Application): AndroidViewModel(application) {
    val allValues: Flow<List<Character>>
    private val repository: CharacterRepository
    private var _selectedCharacter = MutableLiveData<Character>()
    val selectedCharacter : LiveData<Character> = _selectedCharacter

    init {
        _selectedCharacter.value= basicData()
        val dao= AppDataBase.getInstance(application).characterDAO()
        repository= CharacterRepository(dao)
        allValues= repository.allValues
    }

    fun setSelectedCharacter(character: Character){
        _selectedCharacter.value=character
    }


    fun basicData(): Character{
        return Character(-1, "","","","","","","" )
    }


    fun insert(value: Character){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(value)
        }
    }

    fun update(value: Character){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(value)
        }
    }

    fun getCLient(id: Int): Character{
        return repository.getValue(id)
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}