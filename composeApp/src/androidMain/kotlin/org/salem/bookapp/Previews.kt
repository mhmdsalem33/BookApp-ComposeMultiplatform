package org.salem.bookapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.salem.bookapp.book.domain.model.Book
import org.salem.bookapp.book.presentation.book_list.BookListScreenContent
import org.salem.bookapp.book.presentation.book_list.BookListState
import org.salem.bookapp.book.presentation.book_list.component.BookListItem
import org.salem.bookapp.book.presentation.book_list.component.BookSearchBar


@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun BookSearchBarPreview() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    )
    {
        BookSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }

}


@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
fun BookImagePreview() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    )
    {
        BookListItem(
            book = Book(
                id = "1",
                title = "Kotlin Programming",
                authors = listOf("Matthew Mathis"),
                description = "Kotlin",
                languages = listOf("Ar", "En"),
                firstPublishYear = "2025",
                averageRating = 4.10,
                ratingCount = 100,
                numPages = 100,
                numEditions = 100,
                imageUrl = "",
                coverAlternativeKey = 1
            ),

            )
    }
}


private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        authors = listOf("Matthew Mathis"),
        description = "Kotlin",
        languages = listOf("Ar", "En"),
        firstPublishYear = "2025",
        averageRating = 4.10,
        ratingCount = 100,
        numPages = 100,
        numEditions = 100,
        imageUrl = "",
        coverAlternativeKey = 1
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true,)
@Composable
fun BookListScreenPreview() {
    BookListScreenContent(
        state = BookListState(
            searchResults = books,
        ),
        onAction = {

        }
    )
}

