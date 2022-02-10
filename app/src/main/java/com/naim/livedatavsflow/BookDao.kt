package com.naim.livedatavsflow

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.naim.livedatavsflow.model.Book

@Dao
interface BookDao {
    @Query("select * from book")
    fun getAll(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(book: Book)
}