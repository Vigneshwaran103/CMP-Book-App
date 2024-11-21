package com.plcoding.bookpedia.book.presentation.book_detail

import com.plcoding.bookpedia.book.domian.Book

sealed interface BookDetailAction {

    data object OnBackClick : BookDetailAction
    data object OnFavouriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}