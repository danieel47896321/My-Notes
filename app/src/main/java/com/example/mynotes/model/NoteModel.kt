package com.example.mynotes.model

import androidx.lifecycle.ViewModel
import com.example.mynotes.myClass.MyNote

class NoteModel: ViewModel(){
    lateinit var note: MyNote
    var noteViewID = "setCurrentNoteView"
}