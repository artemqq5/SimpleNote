package com.notekoobsiml.booketon.repository.room

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Keep
@Parcelize
@Entity(tableName = "note_dt")
data class Note(
    @PrimaryKey val uid: UUID = UUID.randomUUID(),
    val main_text: String?,
    val sub_text: String?
): Parcelable
