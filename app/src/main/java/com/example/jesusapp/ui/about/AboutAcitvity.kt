package com.example.jesusapp.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jesusapp.R
import kotlinx.android.synthetic.main.content_about_acitvity.*

class AboutAcitvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_acitvity)

        home.setOnClickListener {
            txt_info.textSize = 20f
        }


    }
}