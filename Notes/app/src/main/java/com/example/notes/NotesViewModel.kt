package com.example.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.notes.model.Notes
import com.example.notes.data.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.initializer
import com.example.notes.model.SearchMode

class NotesViewModel(private val notesDao: NotesDao) : ViewModel() {

    var notes by mutableStateOf(listOf<Notes>())
        private set

    var titleName by mutableStateOf("")
    var itemName by mutableStateOf("")

    var editingNoteId by mutableStateOf<Int?>(null)
        private set

    private val _searchedNote = mutableStateOf<Notes?>(null)
    val searchedNote: Notes? get() = _searchedNote.value

    var searchQuery by mutableStateOf("")
    var searchMode by mutableStateOf(SearchMode.ID)

    var noteType by mutableStateOf("None")
        private set


    init {
        viewModelScope.launch {
            notesDao.getAllNotes().collectLatest { notesList ->
                notes = notesList
            }
        }
    }

    fun addNote() {
        if (itemName.isNotBlank()) {
            val note = Notes(
                id = 0,
                title = if (titleName.isNotBlank()) titleName else "Untitled",
                content = itemName,
                timestamp = System.currentTimeMillis(),
                type=noteType
            )

            viewModelScope.launch(Dispatchers.IO) {
                notesDao.insertNote(note)
            }

            titleName = ""
            itemName = ""
        }
    }

    fun updateTitleName(newTitle: String) {
        titleName = newTitle
    }

    fun updateItemName(newName: String) {
        itemName = newName
    }

    fun searchNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = when (searchMode) {
                SearchMode.ID -> {
                    val id = searchQuery.toIntOrNull()
                    if (id != null) notesDao.getNoteById(id) else emptyList()
                }
                SearchMode.TITLE -> notesDao.searchByTitle(searchQuery)
                else -> notesDao.searchByType(searchQuery)
            }
            notes = result
        }
    }

    fun clearSearch() {
        _searchedNote.value = null
    }

    fun updateNoteType(newType: String) {
        noteType = newType
    }
    fun deleteNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesDao.deleteNote(note)
        }

        if (editingNoteId == note.id) {
            editingNoteId = null
        }
    }

    fun setEditing(note: Notes) {
        editingNoteId = note.id
        titleName = note.title
        itemName = note.content
    }

    fun clearEditing() {
        editingNoteId = null
        titleName = ""
        itemName = ""
    }

    fun updateNoteContent(noteId: Int, newTitle: String, newContent: String, newType:String) {
        val noteToUpdate = notes.find { it.id == noteId } ?: return
        val updatedNote = noteToUpdate.copy(
            title = newTitle,
            content = newContent,
            timestamp = System.currentTimeMillis(),
            type= newType
        )

        viewModelScope.launch(Dispatchers.IO) {
            notesDao.updateNote(updatedNote)
        }

        clearEditing()
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NotesApplication)
                NotesViewModel(application.database.notesDao())
            }
        }
    }
}