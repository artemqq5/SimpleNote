package com.notekoobsiml.booketon.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.notekoobsiml.booketon.repository.room.DataBase
import com.notekoobsiml.booketon.repository.room.Note
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataRepository @Inject constructor(@ApplicationContext context: Context) {

    private val database: DataBase by lazy {
        Room.databaseBuilder(
            context,
            DataBase::class.java, "database-note"
        ).build()
    }

    fun getALL(): Flow<List<Note>> = database.getDao().selectAll()

    suspend fun add(item: Note) {
        database.getDao().addNote(item)
    }

    suspend fun delete(item: Note) {
        database.getDao().deleteNote(item)
    }

    suspend fun update(item: Note) {
        database.getDao().updateNote(item)
    }

}