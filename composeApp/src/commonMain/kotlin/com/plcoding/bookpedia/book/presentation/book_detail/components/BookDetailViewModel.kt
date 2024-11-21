package com.plcoding.bookpedia.book.presentation.book_detail.components

import androidx.lifecycle.ViewModel
import com.plcoding.bookpedia.book.presentation.book_detail.BookDetailAction
import com.plcoding.bookpedia.book.presentation.book_detail.BookDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookDetailViewModel : ViewModel() {

    private val _bookDetailState = MutableStateFlow(BookDetailState())
    val bookDetailState = _bookDetailState.asStateFlow()

    fun onAction(action: BookDetailAction) {
        when (action) {


            BookDetailAction.OnFavouriteClick -> {

            }

            is BookDetailAction.OnSelectedBookChange -> {
                _bookDetailState.update {
                    it.copy(
                        book = action.book
                    )
                }
            }
            else -> Unit
        }
    }
}