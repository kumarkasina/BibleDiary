
package com.example.jesusapp.ui.list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.jesusapp.R
import com.example.jesusapp.data.model.Movie
import com.example.jesusapp.databinding.ActivityMovieListBinding

import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.details.MovieDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity(), OnItemClickListener<Movie> {

    lateinit var activity:Activity
    lateinit var  binding : ActivityMovieListBinding
    val viewModel: MoviesListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity =this
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)
        binding.lifecycleOwner = this
        binding.vm=viewModel

        val adapter = MovieListAdapter(this)
        adapter.setOnBottomReachedListener(bottomReachListener)

        binding.list.adapter = adapter

        viewModel.list.observe(this,{
            adapter.submitList(it)
        })
        viewModel.pageNumber=1;
        viewModel.getMoviesList(page = viewModel.pageNumber)
    }

    val bottomReachListener = object : MovieListAdapter.OnBottomReachedListener {
        override fun onBottomReached(position: Int) {
            viewModel.getMoviesList(page = viewModel.pageNumber)
        }
    }

    override fun onItemClick(movie: Movie, position: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra( "movie_id", movie.id)
        intent.putExtra( "position", position)
        startActivity(intent)
    }

}