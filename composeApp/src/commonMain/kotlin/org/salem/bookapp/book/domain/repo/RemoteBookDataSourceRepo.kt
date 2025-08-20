package org.salem.bookapp.book.domain.repo

import org.salem.bookapp.book.domain.model.Book
import org.salem.bookapp.core.domain.DataError
import org.salem.bookapp.core.domain.Result

interface RemoteBookDataSourceRepo {
    suspend fun searchBooks( query : String, resultLimit : Int ? = null ): Result<List<Book>, DataError.Remote>
}