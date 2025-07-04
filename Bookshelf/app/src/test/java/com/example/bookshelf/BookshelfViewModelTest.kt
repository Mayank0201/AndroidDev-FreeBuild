package com.example.bookshelf


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class BookshelfViewModelTest {


    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: BookShelfViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // 👈 Fix
        viewModel = BookShelfViewModel(FakeBookshelfRepository())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // 👈 Restore
    }

    @Test
    fun `fetchBookShelves loads books correctly`() = runTest {
        // Wait for launched coroutines to finish
        testDispatcher.scheduler.advanceUntilIdle()

        val books = viewModel.bookShelfState.value.list
        assertEquals(1, books.size)
        assertEquals("Fake Book Title", books[0].volumeInfo.title)
    }
}