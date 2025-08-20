package org.salem.bookapp.book.data.mappers

import org.salem.bookapp.book.data.dto.SearchedBookDto
import org.salem.bookapp.book.domain.model.Book


fun SearchedBookDto.toDomainBook(): Book {
    return Book(
        id = this.id?.substringAfterLast("/") ?: "",
        title = this.title ?: "",
        languages = this.languages ?: emptyList(),
        coverAlternativeKey = this.coverAlternativeKey,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        description = null,
        firstPublishYear = this.firstPublishYear.toString(),
        numEditions = this.editionCount ?: 0,
        numPages = this.numberOfPagesMedian,
        ratingCount = this.ratingsCount,
        averageRating = null,
        authors = this.authorNames ?: emptyList()
    )
}

