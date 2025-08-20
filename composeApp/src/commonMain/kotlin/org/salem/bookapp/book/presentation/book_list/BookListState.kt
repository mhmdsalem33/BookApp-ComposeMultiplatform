package org.salem.bookapp.book.presentation.book_list

import org.salem.bookapp.book.domain.model.Book
import org.salem.bookapp.core.Presentation.UiText

data class BookListState(
    val searchQuery : String = "Kotlin",
    val searchResults : List<Book> = emptyList(),
    val favoriteBooks : List<Book> = emptyList(),
    val isLoading : Boolean = true,
    val selectedTapIndex : Int = 0,
    val error : String? = null,
    val errorMessage : UiText ? = null
)
