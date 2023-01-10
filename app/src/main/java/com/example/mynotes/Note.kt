package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.controller.NoteController
import com.example.mynotes.model.NoteModel
import com.example.mynotes.myClass.MyNote

class Note : AppCompatActivity() {
    private lateinit var noteModel: NoteModel
    private lateinit var noteController: NoteController
    private lateinit var noteText: TextView
    private lateinit var title: TextView
    private lateinit var backIcon: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        init()
    }
    private fun init(){
        noteModel = ViewModelProvider(this)[NoteModel::class.java]
        noteController = NoteController(noteModel, this)
        backIcon = findViewById<ImageView>(R.id.backIcon)
        noteText = findViewById(R.id.noteText)
        title = findViewById(R.id.title)
        noteController.setNote((intent.getSerializableExtra("note") as? MyNote)!!)
        setBackIcon()
    }
    fun mainWhenCase(id: String){
        when(id){
            "setCurrentNoteView" -> {
                setTitle(noteController.getNoteTitle())
                setText(noteController.getNoteText())
            }
        }
    }
    private fun setTitle(noteTitle: String) { title.text = noteTitle }
    private fun setText(text: String) { noteText.text = text }
    private fun setBackIcon() { backIcon.setOnClickListener{ onBackPressed() } }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity :: class.java))
        finish()
    }
}