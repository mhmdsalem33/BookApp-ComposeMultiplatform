package org.salem.bookapp.book.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.salem.bookapp.book.data.repo.RemoteBookDataSourceRepoImpl
import org.salem.bookapp.book.domain.repo.RemoteBookDataSourceRepo
import org.salem.bookapp.core.data.HttpClientFactory


val bookDataModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::RemoteBookDataSourceRepoImpl).bind<RemoteBookDataSourceRepo>()

}