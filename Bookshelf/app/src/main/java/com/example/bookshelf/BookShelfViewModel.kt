package com.example.bookshelf

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.BookShelfItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookShelfViewModel(
    private val repository: BookshelfRepository
) : ViewModel() {

    private val _bookShelfState = mutableStateOf(BookShelfState())
    val bookShelfState: State<BookShelfState> = _bookShelfState

    private val _suggestions = MutableStateFlow<List<String>>(emptyList())
    val suggestions: StateFlow<List<String>> = _suggestions

    fun fetchBooks(query: String) {
        viewModelScope.launch {
            _bookShelfState.value = _bookShelfState.value.copy(loading = true, error = null)
            try {
                val response = repository.getBooks(query)
                _bookShelfState.value = _bookShelfState.value.copy(
                    list = response,
                    loading = false
                )
            } catch (e: Exception) {
                _bookShelfState.value = _bookShelfState.value.copy(
                    loading = false,
                    error = e.localizedMessage
                )
            }
        }
    }

    fun fetchSuggestions(prefix: String) {
        if (prefix.isBlank()) {
            _suggestions.value = emptyList()
            return
        }
        viewModelScope.launch {
            try {
                val response = repository.getBooks(prefix)
                _suggestions.value = response.map{ it.volumeInfo.title }
            } catch (e: Exception) {
                _suggestions.value = emptyList()
            }
        }
    }
}

data class BookShelfState(
    val loading: Boolean = true,
    val list: List<BookShelfItem> = emptyList(),
    val error: String? = null
)