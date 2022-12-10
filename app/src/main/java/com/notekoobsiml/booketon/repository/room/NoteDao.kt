package com.notekoobsiml.booketon.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_dt")
    fun selectAll(): Flow<List<Note>>

    @Insert(onConflict = IGNORE)
    suspend fun addNote(item: Note)

    @Delete
    suspend fun deleteNote(item: Note)

    @Update
    suspend fun updateNote(item: Note)

}