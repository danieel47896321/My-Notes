package com.example.mynotes.model

import androidx.lifecycle.ViewModel
import com.example.mynotes.myClass.MyNote
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainModel: ViewModel() {
    var database : DatabaseReference = FirebaseDatabase.getInstance("https://my-notes-92ca2-default-rtdb.europe-west1.firebasedatabase.app").reference.child("Notes")
    var noteList: ArrayList<MyNote> = ArrayList<MyNote>()
    var progressBarID = "progressBarStatus"
    var backIconID = "backIconView"
    var recyclerViewID = "setRecyclerView"
}