package com.example.notes.notes_future.data.repository

import com.example.notes.notes_future.data.data_source.NotesDao
import com.example.notes.notes_future.domain.model.Note
import com.example.notes.notes_future.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val dao: NotesDao
): NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

}