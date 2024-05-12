package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.activity.EditActivity
import com.example.notesapp.adapter.ItemClickListener
import com.example.notesapp.adapter.NoteAdapter
import com.example.notesapp.room.Const
import com.example.notesapp.room.NoteDb
import com.example.notesapp.room.NoteEntity
import com.example.notesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class App : AppCompatActivity(), ItemClickListener {
    private lateinit var binding: ActivityMainBinding

    lateinit var noteAdapter: NoteAdapter
    val db by lazy { NoteDb(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addNotes()
        setRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        onLoad()
    }

    private fun onLoad(){
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            withContext(Dispatchers.Main) {
                noteAdapter.setNoteList(notes)
            }
        }
    }

    private fun setRecyclerView() {
        noteAdapter = NoteAdapter(arrayListOf(), this)
        binding.listNote.apply {
            layoutManager =
                LinearLayoutManager(applicationContext)
            adapter = noteAdapter
        }
    }

    private fun addNotes() {
        binding.buttonCreate.setOnClickListener {
            intentOption(0, Const.TYPE_CREATE.ordinal)
        }
    }

    override fun onRead(notes: NoteEntity) {
        intentOption(notes.id, Const.TYPE_READ.ordinal)
    }

    override fun onUpdate(notes: NoteEntity) {
        intentOption(notes.id, Const.TYPE_UPDATE.ordinal)
    }

    override fun onDelete(notes: NoteEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            db.noteDao().deletedata(notes)
            onLoad()
        }
    }

    private fun intentOption(noteId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra(LIST_NOTE_ID, noteId)
                .putExtra(LIST_INTENT_TYPE, intentType )
        )
    }




    companion object {
        const val LIST_NOTE_ID = "list_noteid"
        const val LIST_INTENT_TYPE = "list_intenttype"
    }
}