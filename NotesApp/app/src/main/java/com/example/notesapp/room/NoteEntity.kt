package com.example.notesapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField

@Entity(tableName = "note")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id") var id : Int,
    @ColumnInfo(name = "title") var  title: String,
    @ColumnInfo(name = "description") var  desc: String
)