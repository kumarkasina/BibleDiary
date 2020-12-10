package com.example.jesusapp.ui.directors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jesusapp.R
import kotlinx.android.synthetic.main.activity_directors.*
import kotlinx.android.synthetic.main.content_about_acitvity.home

class DirectorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directors)

        home.setOnClickListener {
            txt_dir_info.textSize = 20f
        }

    }
}