package com.example.bookshelf.data

import com.example.bookshelf.model.BookShelfItem
import com.example.bookshelf.network.BookShelfApiService

interface BookshelfRepository {
    suspend fun getBooks(query: String): List<BookShelfItem>
}

class NetworkBookshelfRepository(
    private val apiService: BookShelfApiService
) : BookshelfRepository {
    override suspend fun getBooks(query: String): List<BookShelfItem> {
        return apiService.getBooks(query).items
    }
}