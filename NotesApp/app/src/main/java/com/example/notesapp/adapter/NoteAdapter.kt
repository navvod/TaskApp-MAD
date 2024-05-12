package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.room.NoteEntity
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNoteBinding


class NoteAdapter(val list:ArrayList<NoteEntity>, private val itemClickListener: ItemClickListener):
    RecyclerView.Adapter<NoteViewHolder>(){
    private lateinit var binding: ItemNoteBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        binding = ItemNoteBinding.bind(view)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val notelist = list[position]
        holder.title.text = notelist.title
        holder.title.setOnClickListener {
            itemClickListener.onRead(notelist)
        }

        binding.iconEdit.setOnClickListener{
            itemClickListener.onUpdate(notelist)
        }

        binding.iconDelete.setOnClickListener {
            itemClickListener.onDelete(notelist)
        }
    }

    override fun getItemCount() = list.size


    fun setNoteList (lists: List<NoteEntity>){
        list.clear()
        list.addAll(lists)
        notifyDataSetChanged()
    }



}