package com.naim.livedatavsflow.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "book_name") val bookName: String,
    @NonNull @ColumnInfo(name = "author_name") val authorName: String
)
