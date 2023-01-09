package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotes.myClass.MyNote

class Note : AppCompatActivity() {
    private lateinit var noteText : TextView
    private lateinit var title : TextView
    private lateinit var myNote: MyNote
    private lateinit var backIcon : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        init()
    }
    private fun init(){
        intent = intent
        myNote = (intent.getSerializableExtra("note") as? MyNote)!!
        backIcon = findViewById<ImageView>(R.id.backIcon)
        noteText = findViewById(R.id.noteText)
        title = findViewById(R.id.title)
        title.text = myNote.noteTitle
        noteText.text = myNote.noteText
        setBackIcon()
    }
    private fun setBackIcon() {
        backIcon.setOnClickListener{
            onBackPressed()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity :: class.java))
        finish()
    }
}