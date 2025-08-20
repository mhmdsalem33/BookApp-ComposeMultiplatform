package org.salem.bookapp.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.salem.bookapp.book.domain.model.Book
import org.salem.bookapp.book.domain.usecases.SearchBooksUseCase
import org.salem.bookapp.core.Presentation.toUiText
import org.salem.bookapp.core.domain.onError
import org.salem.bookapp.core.domain.onSuccess

@OptIn(FlowPreview::class)
class BookListViewModel(
    private val searchBooksUseCase: SearchBooksUseCase
) : ViewModel() {


    private val cachedBooks = emptyList<Book>()

    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null


    init {
        observeSearchQuery()
    }



    fun onAction(action: BookListActions) {
        when (action) {
            is BookListActions.OnBookClick -> {

            }

            is BookListActions.OnSearchQueryChange -> {
                _state.update { it.copy(searchQuery = action.query) }
            }

            is BookListActions.OnTapSelected -> {
                _state.update { it.copy(selectedTapIndex = action.index) }
            }
        }
    }


    private fun observeSearchQuery() {
        _state.map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResults = cachedBooks
                            )
                        }
                    }

                    query.isNotEmpty() -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        searchBooksUseCase(query)
            .onSuccess { searchResults ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResults = searchResults
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }

            }
    }


}