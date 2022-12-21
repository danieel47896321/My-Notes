package com.example.mynotes

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.Adapter.NoteAdapter
import com.example.mynotes.Class.MyNote
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var userSearch : EditText
    private lateinit var recyclerView : RecyclerView
    private var noteList = ArrayList<MyNote>()
    private var database : DatabaseReference = FirebaseDatabase.getInstance("https://my-notes-92ca2-default-rtdb.europe-west1.firebasedatabase.app").reference.child("Notes")
    private lateinit var floatingActionButtonOpen: FloatingActionButton
    private lateinit var floatingActionButtonAdd: ExtendedFloatingActionButton
    private lateinit var floatingActionButtonRemove:ExtendedFloatingActionButton
    private val isOpen = false
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
        setNotes()
    }
    private fun setNotes() {
        val reference = database.ref
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                noteList.clear()
                if(snapshot.exists()){
                    for(note in snapshot.children){
                        val newNote = note.getValue(MyNote :: class.java)
                        noteList.add(newNote!!)
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