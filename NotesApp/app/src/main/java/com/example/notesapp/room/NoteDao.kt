package com.example.notesapp.room

import androidx.room.*



@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata(data: NoteEntity)

    @Update
    fun updatedata(data: NoteEntity)

    @Delete
    fun deletedata(data: NoteEntity)

    @Query("SELECT * from note ORDER BY note_id DESC")
    fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE note_id =:noteid")
   fun getNoteItem(noteid: Int): List<NoteEntity>


}