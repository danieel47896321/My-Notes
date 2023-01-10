package com.example.mynotes.model

import androidx.lifecycle.ViewModel
import com.example.mynotes.adapter.NoteAdapter
import com.example.mynotes.myClass.MyNote

class MainModel: ViewModel() {
    var noteList: ArrayList<MyNote> = ArrayList<MyNote>()
    var progressBarID = "progressBarStatus"
    var backIconID = "backIconView"
    var recyclerViewID = "setRecyclerView"
}