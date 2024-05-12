package com.example.notesapp.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.notesapp.App.Companion.LIST_INTENT_TYPE
import com.example.notesapp.App.Companion.LIST_NOTE_ID
import com.example.notesapp.room.NoteDb
import com.example.notesapp.room.Const
import com.example.notesapp.room.NoteEntity
import com.example.notesapp.databinding.ActivityEditBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    val db by lazy { NoteDb(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewDetail()
    }

    private fun setupViewDetail() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val itype = intent.getIntExtra(LIST_INTENT_TYPE, 0)
        when (itype) {
            Const.TYPE_CREATE.ordinal -> {
                binding.buttonUpdate.visibility = View.GONE
                setClickListener()
            }
            Const.TYPE_READ.ordinal -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                setReadData()
            }
            Const.TYPE_UPDATE.ordinal -> {
                binding.buttonSave.visibility = View.GONE
                setReadData()
                setClickListener()
            }
        }
    }

    private fun setClickListener() {
        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().insertdata(
                    NoteEntity(
                        0,
                        binding.editTitle.text.toString(),
                        binding.editNote.text.toString()
                    )
                )
                finish()
            }
        }

        binding.buttonUpdate.setOnClickListener {
            val noteId = intent.getIntExtra(LIST_NOTE_ID, 0)
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().updatedata(
                    NoteEntity(
                        noteId,
                        binding.editTitle.text.toString(),
                        binding.editNote.text.toString()
                    )
                )
                finish()
            }
        }
    }

    private fun setReadData() {
        val noteId = intent.getIntExtra(LIST_NOTE_ID, 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNoteItem(noteId)[0]
            binding.editTitle.setText(notes.title)
            binding.editNote.setText(notes.desc)
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}