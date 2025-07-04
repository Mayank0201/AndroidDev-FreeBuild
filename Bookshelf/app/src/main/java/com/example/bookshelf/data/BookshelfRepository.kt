package com.example.bookshelf.data

import android.util.Log
import com.example.bookshelf.model.BookShelfItem
import com.example.bookshelf.network.BookShelfApiService

interface BookshelfRepository{
    suspend fun getBooks(query: String): List<BookShelfItem>
}

class NetworkBookshelfRepository(private val apiService: BookShelfApiService): BookshelfRepository
{
    override suspend fun getBooks(query: String): List<BookShelfItem> {
        return try {
            val response = apiService.getBooks(query)
            response.items
        } catch (e: Exception) {
            Log.e("Repo", "Error: ${e.message}")
            emptyList()
        }
    }
}