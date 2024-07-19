package com.example.swapp.dao


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "character")
data class Character(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="height") val height: String,
    @ColumnInfo(name="mass") val mass: String,
    @ColumnInfo(name="gender") val gender: String,
    @ColumnInfo(name="homeworld") val homeworld: String,
    @ColumnInfo(name="wiki") val wiki: String,
    @ColumnInfo(name="image") val image: String
)
