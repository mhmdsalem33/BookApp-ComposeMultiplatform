package org.salem.bookapp.book.presentation.book_details

import org.salem.bookapp.book.domain.model.Book

data class BookDetailState(
    val isLoading : Boolean = true,
    val isFavorite : Boolean = false,
    val book : Book? = null,
)
