package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Note : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
}