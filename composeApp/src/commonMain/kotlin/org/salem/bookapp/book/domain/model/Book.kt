package org.salem.bookapp.book.domain.model

data class Book(
    val id :String,
    val title : String,
    val authors : List<String>,
    val description : String?,
    val languages : List<String>,
    val firstPublishYear : String?,
    val averageRating : Double?,
    val ratingCount :Int?,
    val numPages:Int?,
    val numEditions:Int?,
    val imageUrl : String?,
    val coverAlternativeKey : Int?,
)
