package com.example.swapp.favorites

import com.example.swapp.dao.Character
import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swapp.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class CharacterFavViewModel( application: Application): AndroidViewModel(application) {
    val allValues: Flow<List<CharacterFav>>
    private val repository: CharacterFavRepository

    init {
        val dao= AppDataBase.getInstance(application).characterFavDAO()
        repository= CharacterFavRepository(dao)
        allValues=repository.allValues
    }


    fun basicData(): CharacterFav{
        return CharacterFav(-1 );
    }


    fun insert(value: CharacterFav){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(value)
        }
    }

    fun update(value: CharacterFav){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(value)
        }
    }

    fun getCLient(id: Int): CharacterFav {
        return repository.getValue(id)
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}