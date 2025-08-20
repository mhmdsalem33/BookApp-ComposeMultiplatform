package org.salem.bookapp.book.domain.usecases

import org.salem.bookapp.book.domain.repo.RemoteBookDataSourceRepo

class SearchBooksUseCase (
    private val remoteBookRepo : RemoteBookDataSourceRepo
) {
    suspend operator fun invoke(query : String, resultLimit : Int ? = null ) = remoteBookRepo.searchBooks(query, resultLimit)
}