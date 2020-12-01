package com.example.jesusapp.data.remote.datasource

import com.example.jesusapp.data.remote.Apis
import com.example.jesusapp.data.remote.BaseDataSource

class MovieListDataSource constructor(val service: Apis): BaseDataSource() {
    suspend fun getMoviesList(page:Int) = getResult { service.getMoviesList(page) }
    suspend fun getMovieDetails(movie_id:Int) = getResult { service.getMovieDetails(movie_id) }
}