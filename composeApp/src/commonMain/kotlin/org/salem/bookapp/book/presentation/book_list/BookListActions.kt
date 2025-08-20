package org.salem.bookapp.book.presentation.book_list

import org.salem.bookapp.book.domain.model.Book

interface BookListActions {
    data class OnSearchQueryChange(val query: String) : BookListActions
    data class OnBookClick(val book: Book) : BookListActions
    data class OnTapSelected(val index: Int ) : BookListActions
}