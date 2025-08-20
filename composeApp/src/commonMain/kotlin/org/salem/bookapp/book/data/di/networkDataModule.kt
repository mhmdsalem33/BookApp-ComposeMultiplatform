package org.salem.bookapp.book.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.salem.bookapp.book.data.network.KtorRemoteBookDataSource

val networkDataModule = module {
    singleOf(::KtorRemoteBookDataSource)
}