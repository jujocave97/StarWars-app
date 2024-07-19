package com.example.swapp.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.swapp.dao.CharacterDAO
import com.example.swapp.favorites.CharacterFav
import com.example.swapp.favorites.CharacterFavDAO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import com.example.swapp.dao.Character

@Database(entities = [Character:: class, CharacterFav::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase(){
    abstract  fun characterDAO(): CharacterDAO
    abstract fun characterFavDAO(): CharacterFavDAO // seguir con el characterFAVDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): AppDataBase{
            val databaseName= "SWCharactersDatabase"
            val tempInstance= INSTANCE
            if (tempInstance != null && (tempInstance.openHelper.databaseName ?: "") == databaseName){
                return tempInstance
            }

            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    databaseName)
                    .build()
                INSTANCE= instance

                return instance
            }

        }

    }
}