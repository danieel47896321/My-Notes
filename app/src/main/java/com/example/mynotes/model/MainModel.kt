package com.example.mynotes.model

import androidx.lifecycle.ViewModel
import com.example.mynotes.myClass.MyNote

class MainModel: ViewModel() {
    var noteList: ArrayList<MyNote> = ArrayList<MyNote>()
}