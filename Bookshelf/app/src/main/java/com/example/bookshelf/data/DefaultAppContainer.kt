@file:OptIn(ExperimentalSerializationApi::class)
package com.example.bookshelf.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.example.bookshelf.network.BookShelfApiService

class DefaultAppContainer: AppContainer{

    private val baseUrl = "https://www.googleapis.com/books/v1/"

    val json=Json{ignoreUnknownKeys=true}
    private val retrofit= Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val BookShelfService: BookShelfApiService by lazy {
        retrofit.create(BookShelfApiService ::class.java)
    }

    override val bookshelfRepository: BookshelfRepository by lazy {
        NetworkBookshelfRepository(BookShelfService)
    }

}