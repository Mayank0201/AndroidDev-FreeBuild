package com.example.bookshelf

import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.BookShelfItem

class FakeBookshelfRepository(
    private val fakeBooks: List<BookShelfItem> = emptyList()
) : BookshelfRepository {

    override suspend fun getBooks(query: String): List<BookShelfItem> {
        return fakeBooks.filter { it.volumeInfo.title.contains(query, ignoreCase = true) }
    }
}