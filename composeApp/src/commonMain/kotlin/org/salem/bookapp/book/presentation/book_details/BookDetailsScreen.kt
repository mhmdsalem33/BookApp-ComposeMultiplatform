package org.salem.bookapp.book.presentation.book_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.salem.bookapp.book.presentation.book_details.components.BookDetailContent


@Composable
fun BookDetailsScreen(
    viewModel: BookDetailViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookDetailContent(
        state = state,
        onAction = { action ->
            when (action) {
                BookDetailAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },

    )


}