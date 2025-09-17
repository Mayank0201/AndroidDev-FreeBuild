package com.example.bookshelf.model


import kotlinx.serialization.Serializable

//this used since the json object has multiple classes and cant be ignored using ignoreUnknownKeys
@Serializable
data class BookShelf(
    val items: List<BookShelfItem> = emptyList()
)

@Serializable
data class BookShelfItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
)