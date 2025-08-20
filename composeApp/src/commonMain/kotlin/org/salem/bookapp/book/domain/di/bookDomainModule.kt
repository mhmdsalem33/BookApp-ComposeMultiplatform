package org.salem.bookapp.book.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.salem.bookapp.book.domain.usecases.SearchBooksUseCase


val bookDomainModule = module {
    factoryOf(::SearchBooksUseCase)
}