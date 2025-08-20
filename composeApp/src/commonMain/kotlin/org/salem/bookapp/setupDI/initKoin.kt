package org.salem.bookapp.setupDI

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.salem.bookapp.book.data.di.bookDataModule
import org.salem.bookapp.book.data.di.networkDataModule
import org.salem.bookapp.book.domain.di.bookDomainModule
import org.salem.bookapp.book.presentation.di.bookPresentationModule
import org.salem.bookapp.platformModule


fun initKoin(config : KoinAppDeclaration  = {} ){
    startKoin{
        config.invoke(this)
        modules(
            networkDataModule,
            bookDataModule ,
            bookDomainModule ,
            bookPresentationModule ,
            platformModule
        )
    }
}