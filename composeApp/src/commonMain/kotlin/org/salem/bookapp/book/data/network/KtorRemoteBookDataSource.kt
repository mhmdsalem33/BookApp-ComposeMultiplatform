package org.salem.bookapp.book.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.salem.bookapp.book.data.dto.SearchResponseDto
import org.salem.bookapp.core.extensions.safeCall
import org.salem.bookapp.core.domain.DataError
import org.salem.bookapp.core.domain.Result


private val BASAE_URL  = "https://openlibrary.org"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
){
    suspend fun searchBooks(
        query : String,
        resultLimit : Int ? = null,
    ): Result<SearchResponseDto , DataError.Remote>{
        return safeCall {
            httpClient.get(
                urlString = "$BASAE_URL/search.json"
            ){
                parameter("q"  , query)
                parameter("limit" , resultLimit)
                parameter("language" , "eng")
                parameter("fields" , "key,title,cover_i,author_name,first_publish_year,ratings_count,number_of_pages_median,edition_count")
            }
        }
    }

}