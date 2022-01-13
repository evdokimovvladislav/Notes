package com.example.notes.ui.viewPager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.entities.NoteItem
import com.example.notes.model.NoteRepository

class NotesPagerViewModel(private val repository: NoteRepository) : ViewModel() {

    val noteList: LiveData<List<NoteItem>> = repository.getNoteList()

    private val _fragmentId = MutableLiveData<Int>()
    val fragmentId: LiveData<Int>
        get() = _fragmentId

    fun setStartFragmentItemId(noteItemId: Long) {
        _fragmentId.value = noteList.value?.indexOf(repository.getNoteItem(noteItemId))
    }
}