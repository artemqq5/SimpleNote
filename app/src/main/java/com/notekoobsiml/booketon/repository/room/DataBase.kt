package com.notekoobsiml.booketon.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): NoteDao
}