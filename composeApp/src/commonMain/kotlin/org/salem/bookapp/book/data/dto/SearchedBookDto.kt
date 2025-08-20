package org.salem.bookapp.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBookDto(
    @SerialName("key")
    val id  : String?,
    @SerialName("title")
    val title : String ? = null,
    @SerialName("language")
    val languages :List<String> ? = null,
    @SerialName("cover_i")
    val coverAlternativeKey : Int ? = null,
    @SerialName("author_key") val authorKeys : List<String> ? = null,
    @SerialName("author_name1") val authorNames : List<String> ? = null,
    @SerialName("cover_edition_key1") val coverKey : String ? = null,
    @SerialName("first_publish_year1") val firstPublishYear : Int ? = null,
    @SerialName("ratings_count1") val ratingsCount : Int ? = null,
    @SerialName("number_of_pages_median1") val numberOfPagesMedian : Int ? = null,
    @SerialName("edition_count1") val editionCount : Int ? = null
)
