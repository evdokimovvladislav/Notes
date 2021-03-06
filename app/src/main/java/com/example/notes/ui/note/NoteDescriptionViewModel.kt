package com.example.notes.ui.note

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.conventions.Constant
import com.example.notes.entities.NoteItem
import com.example.notes.model.NoteRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NoteDescriptionViewModel(private val repository: NoteRepository) : ViewModel() {

    val noteList: LiveData<List<NoteItem>> = repository.getNoteLiveDataList()

    private val _noteItem = MutableLiveData<NoteItem>()
    val noteItem: LiveData<NoteItem>
        get() = _noteItem

    private val _noteText = MutableLiveData<String>()
    val noteText: LiveData<String>
        get() = _noteText

    private val _textToShare = MutableLiveData<String>()
    val textToShare: LiveData<String>
        get() = _textToShare

    fun setNoteItem(noteItemId: Long) {
        viewModelScope.launch {
            val item = repository.getNoteItem(noteItemId)
            _noteItem.value = item
        }
    }

    fun setNoteText(text: String) {
        _noteText.value = text
    }

    @SuppressLint("SimpleDateFormat")
    fun addNoteItem(title: String, text: String) {
        viewModelScope.launch {
            val currentDate = SimpleDateFormat(Constant.DATE_FORMAT).format(Date())
            repository.addNote(NoteItem(title = title, text = text, dateOfCreation = currentDate))
        }
    }

    fun editNoteItem(title: String, text: String) {
        viewModelScope.launch {
            _noteItem.value?.let {
                val item = it.copy(title = title, text = text)
                repository.addNote(item)
            }
        }
    }

    fun shareNote(title: String, text: String) {
        val noteTextToShare = if (title.isEmpty()) text else "${title}\n${text}"
        _textToShare.value = noteTextToShare
    }
}