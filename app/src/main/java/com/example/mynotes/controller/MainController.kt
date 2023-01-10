package com.example.mynotes.controller

import android.view.View
import com.example.mynotes.MainActivity
import com.example.mynotes.adapter.NoteAdapter
import com.example.mynotes.model.MainModel
import com.example.mynotes.myClass.MyNote
import com.google.firebase.database.*
import java.util.*

class MainController(var mainModel: MainModel, var view: MainActivity) {
    private var database : DatabaseReference = FirebaseDatabase.getInstance("https://my-notes-92ca2-default-rtdb.europe-west1.firebasedatabase.app").reference.child("Notes")
    fun setView() {
        setBackIcon()
        setProgressBar()
        setNotes()
    }
    private fun setBackIcon() { view.mainWhenCase(mainModel.backIconID) }
    private fun setProgressBar() {
        if(mainModel.noteList.isNotEmpty()){
            view.mainWhenCase(mainModel.progressBarID)
        }
    }
    fun getProgressBarStatus(): Int{ return View.GONE }
    fun getBackIconView(): Int{ return View.GONE }
    fun getNoteAdapter(): NoteAdapter { return NoteAdapter(mainModel.noteList) }
    fun setNotes() {
        val reference = database.ref
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mainModel.noteList.clear()
                if (snapshot.exists()) {
                    for (note in snapshot.children) {
                        val newNote = note.getValue(MyNote::class.java)
                        mainModel.noteList.add(newNote!!)
                    }
                    setProgressBar()
                    view.mainWhenCase(mainModel.recyclerViewID)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
    fun userSearchFunc(text: String) {
        val reference = database.ref
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mainModel.noteList.clear()
                if(snapshot.exists()){
                    for(note in snapshot.children){
                        val newNote = note.getValue(MyNote :: class.java)
                        if (newNote != null) {
                            if(newNote.noteText.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT)) || newNote.noteDate.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT)) || newNote.noteTitle.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))) {
                                mainModel.noteList.add(newNote)
                            }
                        }
                    }
                    setProgressBar()
                    view.mainWhenCase(mainModel.recyclerViewID)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}