package com.example.mynotes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.adapter.NoteAdapter
import com.example.mynotes.controller.MainController
import com.example.mynotes.model.MainModel
import com.example.mynotes.myClass.MyNote
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var userSearch : EditText
    private lateinit var recyclerView : RecyclerView
    private lateinit var mainModel: MainModel
    private lateinit var mainController: MainController
    private lateinit var backIcon: ImageView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init () {
        mainModel = ViewModelProvider(this)[MainModel::class.java]
        mainController = MainController(mainModel, this)
        userSearch = findViewById<EditText>(R.id.userSearch)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        backIcon = findViewById<ImageView>(R.id.backIcon)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        mainController.setView()
        noteSearch()
    }
    fun setBackIcon(view: Int) { backIcon.visibility = view }
    fun userSearchText(): String { return userSearch.text.toString() }
    private fun noteSearch() {
        userSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mainController.userSearchFunc(s.toString())
            }
        })
    }
    fun showNotes(gridLayoutManager: GridLayoutManager, noteAdapter: NoteAdapter) {
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = noteAdapter
    }
    fun progressBarStatus(view: Int) { progressBar.visibility = view }
}