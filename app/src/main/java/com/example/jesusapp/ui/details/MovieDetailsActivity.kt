package com.example.jesusapp.ui.details

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.jesusapp.R
import com.example.jesusapp.databinding.ActivityMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    lateinit var activity: Activity
    lateinit var  binding : ActivityMovieDetailsBinding
    val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity =this
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.lifecycleOwner = this
        binding.vm=viewModel

        val id = intent.getIntExtra("movie_id", -1)
        viewModel.getMovieDetails(id)

    }
}