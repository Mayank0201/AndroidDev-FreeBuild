package com.example.bookshelf

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.BookShelfItem
import kotlinx.coroutines.launch

class BookShelfViewModel(
    private val repository: BookshelfRepository
) : ViewModel() {

    private val _bookShelfState = mutableStateOf(BookShelfState())
    val bookShelfState: State<BookShelfState> = _bookShelfState

    init {
        fetchBookShelves("horror")
    }

    fun fetchBookShelves(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getBooks(query)
                _bookShelfState.value = _bookShelfState.value.copy(
                    list = response,
                    loading = false

                )
                Log.d("BookshelfVM", "Loaded ${response.size} books")
            } catch (e: Exception) {
                _bookShelfState.value = _bookShelfState.value.copy(
                    error = "Error fetching data ${e.message}",
                    loading = false
                )
            }
        }
    }

    data class BookShelfState(
        val loading: Boolean = true,
        val list: List<BookShelfItem> = emptyList(),
        val error: String? = null
    )
}