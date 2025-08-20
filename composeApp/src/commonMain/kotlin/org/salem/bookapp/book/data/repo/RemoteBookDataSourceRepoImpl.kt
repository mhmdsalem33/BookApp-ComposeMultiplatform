package org.salem.bookapp.book.data.repo

import org.salem.bookapp.book.data.mappers.toDomainBook
import org.salem.bookapp.book.data.network.KtorRemoteBookDataSource
import org.salem.bookapp.book.domain.model.Book
import org.salem.bookapp.book.domain.repo.RemoteBookDataSourceRepo
import org.salem.bookapp.core.domain.DataError
import org.salem.bookapp.core.domain.Result
import org.salem.bookapp.core.domain.map


class RemoteBookDataSourceRepoImpl(
    private val ktorRemoteBookDataSource: KtorRemoteBookDataSource
) : RemoteBookDataSourceRepo {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<List<Book>, DataError.Remote> {
        return  ktorRemoteBookDataSource.searchBooks(query, resultLimit)
            .map { dtoList ->
                dtoList.results?.map { it.toDomainBook() } ?: emptyList()
            }

    }
}