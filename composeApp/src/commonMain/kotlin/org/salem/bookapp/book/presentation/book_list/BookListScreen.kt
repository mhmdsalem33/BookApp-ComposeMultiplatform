package org.salem.bookapp.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bookapp_composemultiplatform.composeapp.generated.resources.Res
import bookapp_composemultiplatform.composeapp.generated.resources.favorites
import bookapp_composemultiplatform.composeapp.generated.resources.no_favorite_books
import bookapp_composemultiplatform.composeapp.generated.resources.no_search_results
import bookapp_composemultiplatform.composeapp.generated.resources.search_results
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.salem.bookapp.book.domain.model.Book
import org.salem.bookapp.book.presentation.book_list.component.BookList
import org.salem.bookapp.book.presentation.book_list.component.BookSearchBar
import org.salem.bookapp.core.Presentation.DarkBlue
import org.salem.bookapp.core.Presentation.DesertWhite
import org.salem.bookapp.core.Presentation.SandYellow


@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    onBookClick: (Book) -> Unit = {},
    viewModel: BookListViewModel = koinViewModel()
) {


    val bookState by viewModel.state.collectAsStateWithLifecycle()

    BookListScreenContent(
        onBookClick = { book ->
            onBookClick(book)
        },
        onAction = { action ->
            viewModel.onAction(action)
        },
        bookState
    )
}


@Composable
fun BookListScreenContent(
    onBookClick: (Book) -> Unit = {},
    onAction: (BookListActions) -> Unit,
    state: BookListState
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState { 2 }
    val searchResultListState = rememberLazyListState()
    val favoriteResultListState = rememberLazyListState()


    LaunchedEffect(state.searchResults) {
//        searchResultListState.animateScrollToItem(0)
        searchResultListState.scrollToItem(0)
    }


    LaunchedEffect(state.selectedTapIndex) {
        pagerState.animateScrollToPage(state.selectedTapIndex)
    }


    LaunchedEffect(pagerState.currentPage) {
        onAction(BookListActions.OnTapSelected(pagerState.currentPage))
    }


    Column(
        modifier = Modifier.fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(BookListActions.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier.widthIn(max = 400.dp).fillMaxWidth().padding(16.dp)
        )

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = state.selectedTapIndex,
                    modifier = Modifier.padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    containerColor = DesertWhite,
                    contentColor = SandYellow,
                    indicator = { tapPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tapPositions[state.selectedTapIndex]),
                            color = SandYellow
                        )
                    },

                    ) {

                    // Tap 1 Search Results

                    Tab(
                        selected = state.selectedTapIndex == 0,
                        onClick = {
                            onAction(BookListActions.OnTapSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }

                    // Tap 2 Favorites
                    Tab(
                        selected = state.selectedTapIndex == 1,
                        onClick = {
                            onAction(BookListActions.OnTapSelected(1))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favorites),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                //Horizontal Pager
//
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (pageIndex) {
                            0 -> {
                                SearchResults(
                                    state,
                                    searchResultListState,
                                    onBookClick = { book ->
                                        onBookClick(book)
                                    }
                                )
                            }

                            1 -> {
                                FavoriteResults(
                                    state,
                                    favoriteResultListState,
                                    onBookClick = { book -> onBookClick(book) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SearchResults(
    state: BookListState,
    searchLazyListState: LazyListState,
    onBookClick: (Book) -> Unit
) {
    if (state.isLoading) {
        CircularProgressIndicator(color = SandYellow)
    } else {
        when {
            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage.asString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            state.searchResults.isEmpty() -> {
                Text(
                    text = stringResource(Res.string.no_search_results),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            else -> {
                BookList(
                    books = state.searchResults,
                    onBookClick = { book ->
                        onBookClick(book)
                    },
                    modifier = Modifier.fillMaxSize(),
                    scrollState = searchLazyListState
                )
            }
        }
    }
}

@Composable
fun FavoriteResults(
    state: BookListState,
    favoriteResultListState: LazyListState,
    onBookClick: (Book) -> Unit
) {
    if (state.favoriteBooks.isEmpty()) {
        Text(
            text = stringResource(Res.string.no_favorite_books),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.error,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    } else {
        BookList(
            books = state.favoriteBooks,
            onBookClick = { book -> onBookClick(book) },
            modifier = Modifier.fillMaxSize(),
            scrollState = favoriteResultListState
        )
    }
}


@Composable
fun NoRippleTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        color = Color.Transparent
    ) {
        content()
    }
}
