package com.example.jesusapp.ui.details

import com.example.jesusapp.data.remote.response.MovieData
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jesusapp.data.remote.Result
import com.example.jesusapp.data.repo.MovieListRepo
import kotlinx.coroutines.launch

public class MovieDetailsViewModel @ViewModelInject public constructor(val repo: MovieListRepo): ViewModel() {

    var movieDetails = MutableLiveData<MovieData>()
    var showLoader   = MutableLiveData<Boolean>(false)

    fun getMovieDetails(movie_id: Int) {
        showLoader.postValue( true)
        viewModelScope.launch {
            val result = repo.getMovieDetails(movie_id)
            when (result.status) {
                Result.Status.SUCCESS -> {
                    movieDetails.postValue( result.data!! )
                    showLoader.postValue( false)
                }
                Result.Status.ERROR -> {
                }
            }
        }
    }

}