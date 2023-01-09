package com.example.mynotes.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.myClass.MyNote
import com.example.mynotes.Note
import com.example.mynotes.R

class NoteAdapter(var noteList: ArrayList<MyNote>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.note_view,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.noteTitle.text = noteList[position].noteTitle
        holder.noteDate.text = noteList[position].noteDate
        holder.noteText.text = noteList[position].noteText
        holder.constraintLayout.setOnClickListener{
            val intent = Intent(holder.itemView.context, Note::class.java)
            intent.putExtra("note", noteList[position])
            holder.itemView.context.startActivity(intent)
            (holder.itemView.context as Activity).finish()
        }
    }
    override fun getItemCount(): Int { return noteList.size }
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var noteTitle : TextView
        var noteDate : TextView
        var noteText : TextView
        var constraintLayout : ConstraintLayout
        init {
            noteTitle = view.findViewById(R.id.noteTitle)
            noteDate = view.findViewById(R.id.noteDate)
            noteText = view.findViewById(R.id.noteText)
            constraintLayout = view.findViewById(R.id.constraintLayout)
        }
    }
}