package com.plcoding.bookpedia.book.presentation.book_detail

import com.plcoding.bookpedia.book.domian.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavourite: Boolean = false,
    val book: Book? = null
)
