package org.salem.bookapp.book.presentation.book_details.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.salem.bookapp.book.presentation.book_details.BookDetailAction
import org.salem.bookapp.book.presentation.book_details.BookDetailState


@Composable
fun BookDetailContent(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit
) {

    BlurredImageBackground(
        imageURL = state.book?.imageUrl,
        isFavorite = state.isFavorite,
        onFavoriteClick = {
            onAction(BookDetailAction.OnFavoriteClick)
        },
        onBackClick = {
            onAction(BookDetailAction.OnBackClick)
        },
        modifier = Modifier.fillMaxSize()
    ) {

    }

}