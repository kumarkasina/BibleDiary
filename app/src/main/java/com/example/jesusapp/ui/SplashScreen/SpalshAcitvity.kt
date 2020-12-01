package com.example.jesusapp.ui.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.jesusapp.R
import com.example.jesusapp.ui.HomeDetail.PrayerDetailActivity
import com.example.jesusapp.ui.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SpalshAcitvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_acitvity)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // close this activity
            finish()
        }, 5000)


    }
}