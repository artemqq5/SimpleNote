package com.notekoobsiml.booketon.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notekoobsiml.booketon.repository.room.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelRepository @Inject constructor(
    val repository: DataRepository
) : ViewModel() {

    val allNoteDataList: Flow<List<Note>> = repository.getALL()

    fun deleteNote(item: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(item)
        }
    }

    fun insertNote(item: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(item)
        }
    }

    fun updateNote(item: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(item)
        }
    }

}