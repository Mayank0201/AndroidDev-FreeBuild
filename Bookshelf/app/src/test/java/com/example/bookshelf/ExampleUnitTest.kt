package com.example.bookshelf

import org.junit.Test

import org.junit.Assert.*

import kotlinx.coroutines.test.runTest

import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var viewModel: BookShelfViewModel

    @Before
    fun setup() {
        viewModel = BookShelfViewModel(FakeBookshelfRepository())
    }

    @Test
    fun `fetchBookShelves loads books correctly`() = runTest {

        val books = viewModel.bookShelfState.value.list
        assertEquals(1, books.size)
        assertEquals("Fake Book Title", books[0].volumeInfo.title)
        assertFalse(viewModel.bookShelfState.value.loading)
        assertNull(viewModel.bookShelfState.value.error)
    }
}