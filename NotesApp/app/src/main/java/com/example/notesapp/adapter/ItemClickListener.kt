package com.example.notesapp.adapter

import com.example.notesapp.room.NoteEntity


interface ItemClickListener {

    fun onRead(notes: NoteEntity)
    fun onUpdate(notes: NoteEntity)
    fun onDelete(notes:NoteEntity)


}