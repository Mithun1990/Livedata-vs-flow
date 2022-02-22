package com.naim.livedatavsflow

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.naim.livedatavsflow.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("select * from book")
    fun getAll(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(book: Book)

    @Query("select * from book")
    fun getAllNew(): Flow<List<Book>>
}