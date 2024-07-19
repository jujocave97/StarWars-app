package com.example.swapp.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.swapp.dao.Character


@Entity(
    tableName = "favoritos",
    foreignKeys = [ForeignKey(
        entity = Character::class,
        parentColumns = ["id"],
        childColumns = ["characterId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CharacterFav(
    @PrimaryKey val characterId: Int
)