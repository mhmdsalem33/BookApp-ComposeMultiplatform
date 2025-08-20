package org.salem.bookapp.book.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.salem.bookapp.book.domain.model.Book

class SelectedBookViewModel : ViewModel() {


    private val _selectedBook =  MutableStateFlow<Book?>(null)
     val selectedBook = _selectedBook.asStateFlow()

    fun onSelectBook(book :Book?){
        _selectedBook.update { book }

    }

}