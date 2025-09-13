package com.example.artspace

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArtViewModel : ViewModel() {

    private val _result = MutableStateFlow(0)
    val result: StateFlow<Int> = _result

    fun next() {
        val r = _result.value
        _result.value = if (r in 0..2) r + 1 else 0
    }

    fun prev() {
        val r = _result.value
        _result.value = if (r in 1..3) r - 1 else 3
    }
}
