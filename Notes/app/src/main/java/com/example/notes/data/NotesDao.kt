package com.example.notes.data

import androidx.room.*
import com.example.notes.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes):Long

    @Update
    suspend fun updateNote(note: Notes):Int

    @Delete
    suspend fun deleteNote(note: Notes):Int

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): List<Notes>

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :title || '%' COLLATE NOCASE")
    suspend fun searchByTitle(title: String): List<Notes>

    @Query("SELECT * FROM notes WHERE type LIKE :q || '%'")
    suspend fun searchByType(q: String): List<Notes>
}
