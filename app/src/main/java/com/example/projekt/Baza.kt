package com.example.projekt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (
    entities = arrayOf(Uporabnik::class),
    version = 1
)

abstract class Baza: RoomDatabase() {

    abstract fun uporabnikDao(): UporabnikDao

    companion object {
        @Volatile
        private var INSTANCE: Baza? = null

        fun getDatabase(context: Context): Baza {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Baza::class.java,
                    "baza_uporabnikov"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}