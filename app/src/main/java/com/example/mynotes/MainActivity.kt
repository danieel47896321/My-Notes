package com.example.mynotes

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.Adapter.NoteAdapter
import com.example.mynotes.Class.MyNote
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var userSearch : EditText
    private lateinit var recyclerView : RecyclerView
    private var noteList = ArrayList<MyNote>()
    private val floatingActionButtonOpen: FloatingActionButton? = null
    private val floatingActionButtonAdd: ExtendedFloatingActionButton? = null
    private var floatingActionButtonRemove:ExtendedFloatingActionButton? = null
    private val isOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init () {
        userSearch = findViewById<EditText>(R.id.userSearch)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        setNotes()
    }
    private fun setNotes() {
        noteList.add(MyNote("work","1/1/1990","test text to check"))
        noteList.add(MyNote("work","1/1/1990","test text to check"))
        noteList.add(MyNote("work","1/1/1990","test text to check"))
        showNotes()
    }
    private fun showNotes(){
        val noteAdapter = NoteAdapter(noteList)
        recyclerView.layoutManager = GridLayoutManager(this,1)
        recyclerView.adapter = noteAdapter
    }
}