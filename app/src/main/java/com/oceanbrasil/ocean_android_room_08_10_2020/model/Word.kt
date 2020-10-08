package com.oceanbrasil.ocean_android_room_08_10_2020.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table") // TableName é opcional
data class Word(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word_data") // ColumnInfo é opcional
    val word: String
)
