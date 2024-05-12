package com.example.notesapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R


class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val title = itemView.findViewById<TextView>(R.id.text_title)

}
