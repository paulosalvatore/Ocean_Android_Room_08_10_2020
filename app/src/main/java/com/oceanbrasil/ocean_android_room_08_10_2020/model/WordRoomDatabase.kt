package com.oceanbrasil.ocean_android_room_08_10_2020.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private val roomDatabaseCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                instance?.let {
                    Thread(Runnable {
                        val dao = it.wordDao()

                        dao.deleteAll()

                        val word = Word("Samsung")
                        dao.insert(word)

                        val word2 = Word("Ocean")
                        dao.insert(word2)
                    }).start()
                }
            }
        }

        private var instance: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            if (instance == null) {
                synchronized(WordRoomDatabase::class.java) {
                    // Criação do banco de dados
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                    ).addCallback(roomDatabaseCallback).build()
                }
            }

            return instance!!
        }
    }
}
