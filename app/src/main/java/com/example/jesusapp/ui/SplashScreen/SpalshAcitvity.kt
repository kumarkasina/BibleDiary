package com.example.jesusapp.ui.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.jesusapp.R
import com.example.jesusapp.ui.latestnews.NewsActivity

class SpalshAcitvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_acitvity)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)

            // close this activity
            finish()
        }, 5000)


    }
}