package com.example.notes.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notes.entities.NoteItem
import com.example.notes.model.NoteRepository

class NoteListViewModel(private val repository: NoteRepository) : ViewModel() {

    val noteList: LiveData<List<NoteItem>> = repository.getNoteList()

    fun deleteNoteItem(noteItemId: Long) {
        repository.deleteNote(noteItemId)
    }
}