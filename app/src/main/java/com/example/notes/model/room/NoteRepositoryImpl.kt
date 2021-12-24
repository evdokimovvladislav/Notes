package com.example.notes.model.room

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notes.NoteItem
import com.example.notes.model.NoteDatabase
import com.example.notes.model.NoteRepository

class NoteRepositoryImpl(context: Context) : NoteRepository {

    private val noteDB = NoteDatabase.getInstance(context)

    override fun getNoteList(): LiveData<List<NoteItem>> = noteDB.noteDao().getNoteList()

    override fun getNoteItem(noteItemId: Long): NoteItem {
        return noteDB.noteDao().getNote(noteItemId)
    }

    override fun addNote(note: NoteItem) {
        noteDB.noteDao().insertNote(note)
    }

    override fun deleteNote(id: Long) {
        noteDB.noteDao().deleteNote(id)
    }
}