package com.example.mynotes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.Adapter.NoteAdapter
import com.example.mynotes.Class.MyNote
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var userSearch : EditText
    private lateinit var recyclerView : RecyclerView
    private var noteList = ArrayList<MyNote>()
    private var database : DatabaseReference = FirebaseDatabase.getInstance("https://my-notes-92ca2-default-rtdb.europe-west1.firebasedatabase.app").reference.child("Notes")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init () {
        userSearch = findViewById<EditText>(R.id.userSearch)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val backIcon = findViewById<ImageView>(R.id.backIcon)
        backIcon.visibility = View.GONE
        userSearch.setText("")
        setNotes()
        noteSearch()
    }
    private fun setNotes() {
        val reference = database.ref
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(userSearch.text.toString() == "") {
                    noteList.clear()
                    if (snapshot.exists()) {
                        for (note in snapshot.children) {
                            val newNote = note.getValue(MyNote::class.java)
                            noteList.add(newNote!!)
                        }
                        showNotes()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
    private fun noteSearch() {
        userSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                userSearchFunc(s.toString())
            }
        })
    }
    private fun userSearchFunc(text : String) {
        val reference = database.ref
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               noteList.clear()
                if(snapshot.exists()){
                    for(note in snapshot.children){
                        val newNote = note.getValue(MyNote :: class.java)
                        if (newNote != null) {
                            if(newNote.noteText.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT)) || newNote.noteDate.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT)) || newNote.noteTitle.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))) {
                                noteList.add(newNote)
                            }
                        }
                    }
                    showNotes()
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
    private fun showNotes(){
        val noteAdapter = NoteAdapter(noteList)
        recyclerView.layoutManager = GridLayoutManager(this,1)
        recyclerView.adapter = noteAdapter
    }
}