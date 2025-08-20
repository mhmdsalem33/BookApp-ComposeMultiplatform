package org.salem.bookapp.book.presentation.book_details

import org.salem.bookapp.book.domain.model.Book

sealed interface  BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavoriteClick : BookDetailAction
    data class  OnSelectedBookChange( val book : Book ) : BookDetailAction
}
