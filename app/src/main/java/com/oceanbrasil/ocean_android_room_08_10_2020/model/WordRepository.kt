package com.oceanbrasil.ocean_android_room_08_10_2020.model

import android.app.Application
import androidx.lifecycle.LiveData

class WordRepository(application: Application) {

    private val wordDao: WordDao

    val allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        wordDao = db.wordDao()

        allWords = wordDao.getAllWords()
    }

    fun insert(word: Word) {
        Thread(Runnable {
            wordDao.insert(word)
        }).start()
    }
}
