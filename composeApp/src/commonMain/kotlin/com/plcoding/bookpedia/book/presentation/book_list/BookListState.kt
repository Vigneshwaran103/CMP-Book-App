package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domian.Book
import com.plcoding.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery : String = "kotlin",
    val searchResults : List<Book> = emptyList(),
    val favouriteBooks : List<Book> = emptyList(),
    val isLoading : Boolean = false,
    val selectedTabIndex : Int = 0,
    val errorMessage : UiText? = null
)

val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        description = "Description $it",
        authors = listOf("Vignesh"),
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.5678,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}
