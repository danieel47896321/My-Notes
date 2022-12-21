package com.example.mynotes.Class

import java.io.Serializable

class MyNote : Serializable {
    lateinit var noteTitle: String
    lateinit var noteDate: String
    lateinit var noteText: String
    constructor(noteTitle: String, noteDate: String, noteText: String) {
        this.noteTitle = noteTitle
        this.noteDate = noteDate
        this.noteText = noteText
    }
    constructor(){}
}