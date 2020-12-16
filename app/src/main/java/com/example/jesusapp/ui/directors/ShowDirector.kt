package com.example.jesusapp.ui.directors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jesusapp.R
import kotlinx.android.synthetic.main.activity_show_director.*

class ShowDirector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_director)

        txt_director.text = intent.getStringExtra("name")
    }
}