package org.salem.bookapp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.salem.bookapp.book.presentation.SelectedBookViewModel
import org.salem.bookapp.book.presentation.book_details.BookDetailAction
import org.salem.bookapp.book.presentation.book_details.BookDetailViewModel
import org.salem.bookapp.book.presentation.book_details.BookDetailsScreen
import org.salem.bookapp.book.presentation.book_list.BookListScreen
import org.salem.bookapp.book.presentation.book_list.BookListViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.BookGraph
        ) {


            navigation<Routes.BookGraph>(
                startDestination = Routes.BookList
            ) {

                // Book List Screen
                composable<Routes.BookList> {

                    val viewModel = koinViewModel<BookListViewModel>()

                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    BookListScreen(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.onSelectBook(book)
                            navController.navigate(Routes.BookDetails(book.id))
                    })
                }

                // Book Details Screen
                composable<Routes.BookDetails> { it ->


                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    val viewModel = koinViewModel<BookDetailViewModel>()


                    val selectedBook = selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                    val args = it.toRoute<Routes.BookDetails>()


//                    LaunchedEffect(true) {
//                        selectedBookViewModel.onSelectBook(null )
//                    }



                    LaunchedEffect(selectedBook){
                        selectedBook.value?.let {
                            viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        BookDetailsScreen(
                            viewModel = viewModel,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }


//

                }
            }
        }
    }
}