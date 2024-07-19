package com.example.swapp.model


import android.util.Log
import com.example.swapp.dao.CharacterViewModel
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.net.URL
import com.google.gson.Gson
import com.example.swapp.dao.Character

fun loadData(characterViewModel: CharacterViewModel, allCharacters: List<Character>){
    try{
        CoroutineScope(Dispatchers.IO).launch {
            val apiResponse= URL("https://jujocave97.github.io/apipdm/swapi.json").readText()

            val type= object: TypeToken<List<Character>>() {}.type
            val characterList= Gson().fromJson<List<Character>>(apiResponse, type)

            if(allCharacters.isEmpty()){
                for(character in characterList){
                    characterViewModel.insert(character)
                }
            }else{
                if(allCharacters.size== characterList.size && allCharacters.sortedBy { it.id }!= characterList){
                    for(character in characterList){
                        characterViewModel.update(character)
                    }
                }
            }
            cancel()
        }
    }catch (e: Exception){
        Log.i("Error", e.message.toString())
    }
}