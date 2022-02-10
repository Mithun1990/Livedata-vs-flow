package com.naim.livedatavsflow.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naim.livedatavsflow.BookDao
import com.naim.livedatavsflow.model.Book

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, "live_data_vs_flow.db")
                        .build()
                INSTANCE = instance
                instance
            }
        }

    }
}