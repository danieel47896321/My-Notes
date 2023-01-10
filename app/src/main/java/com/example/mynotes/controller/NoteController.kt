package com.example.mynotes.controller

import com.example.mynotes.Note
import com.example.mynotes.model.NoteModel
import com.example.mynotes.myClass.MyNote

class NoteController (var noteModel: NoteModel, var view: Note) {
    fun setNote(myNote: MyNote) {
        noteModel.note = myNote
        view.mainWhenCase(noteModel.noteViewID)
    }
    fun getNoteTitle(): String{ return noteModel.note.noteTitle }
    fun getNoteText(): String{ return noteModel.note.noteText }
}