package com.plcoding.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.plcoding.bookpedia.book.data.database.FavouriteBookDao
import com.plcoding.bookpedia.book.data.mappers.toBook
import com.plcoding.bookpedia.book.data.mappers.toBookEntity
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.domian.Book
import com.plcoding.bookpedia.book.domian.BookRepository
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataResponse: RemoteBookDataSource,
    private val favouriteBookDao: FavouriteBookDao
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataResponse.searchBooks(query)
            .map { dto ->
                dto.results.map {
                    it.toBook()
                }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favouriteBookDao.getFavouriteBook(bookId = bookId)
        return if (localResult == null) {
            remoteBookDataResponse.getBookDetails(
                bookWordId = bookId
            ).map {
                it.description
            }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavouriteBooks(): Flow<List<Book>> {
        return favouriteBookDao.getFavouriteBooks()
            .map { bookListEntities ->
                bookListEntities.map {
                    it.toBook()
                }
            }
    }

    override fun isBookFavourite(bookId: String): Flow<Boolean> {
        return favouriteBookDao
            .getFavouriteBooks()
            .map { bookListEntities ->
                bookListEntities.any {
                    it.id == bookId
                }
            }
    }

    override suspend fun markAsFavourites(book: Book): EmptyResult<DataError.Local> {
        return try {
            favouriteBookDao.upsert(
                book = book.toBookEntity()
            )
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavourites(bookId: String) {
        favouriteBookDao.deleteFavouriteBook(bookId = bookId)
    }
}