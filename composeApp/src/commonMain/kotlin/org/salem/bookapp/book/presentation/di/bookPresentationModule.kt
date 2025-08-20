package org.salem.bookapp.book.presentation.di

import org.koin.dsl.module
import org.salem.bookapp.book.presentation.book_list.BookListViewModel
import org.salem.bookapp.book.presentation.SelectedBookViewModel
import org.salem.bookapp.book.presentation.book_details.BookDetailViewModel
import org.koin.core.module.dsl.*


val bookPresentationModule = module {
    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)
}