package com.plcoding.bookpedia.book.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.plcoding.bookpedia.book.domian.Book
import kotlinx.coroutines.flow.Flow


@Dao
interface FavouriteBookDao {
    @Upsert
    suspend fun upsert(book: BookEntity)

    @Query("SELECT * FROM BookEntity")
    fun getFavouriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity where id = :bookId")
    suspend fun getFavouriteBook(bookId: String): BookEntity?

    @Query("DELETE FROM BookEntity where id = :bookId")
    suspend fun deleteFavouriteBook(bookId: String)
}