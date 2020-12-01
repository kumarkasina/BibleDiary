package com.example.jesusapp.ui

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.example.jesusapp.R

import com.example.jesusapp.ui.home.MovieHelper
import com.example.jesusapp.ui.home.MoviesPagerAdapter
import com.example.jesusapp.utils.ConnectionType
import com.example.jesusapp.utils.NetworkMonitorUtil

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var pagerAdapter: MoviesPagerAdapter

    private val networkMonitor = NetworkMonitorUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                // internet_status.text = "Wifi Connection"
                                Log.e("true","Wifi Connection")
                            }
                            ConnectionType.Cellular -> {
                                //  internet_status.text = "Cellular Connection"
                                Log.e("true","Cellular Connection")
                            }
                            else -> { }
                        }
                    }
                    false -> {
                        // internet_status.text = "No Connection"
                        Log.e("false","No Connection")
                    }
                }
            }
        }


        val movies = MovieHelper.getMoviesFromJson("movies.json", this)
        pagerAdapter = MoviesPagerAdapter(supportFragmentManager, movies)
        viewPager.adapter = pagerAdapter
        dots_indicator.setViewPager(viewPager)

    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}