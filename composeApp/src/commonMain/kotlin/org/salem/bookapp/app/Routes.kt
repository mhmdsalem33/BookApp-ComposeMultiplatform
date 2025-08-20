package org.salem.bookapp.app

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object BookGraph : Routes

    @Serializable
    data object BookList : Routes

    @Serializable
    data class BookDetails( val id : String )

}