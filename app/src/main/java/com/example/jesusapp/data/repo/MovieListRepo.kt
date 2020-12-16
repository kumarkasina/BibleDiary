package com.example.jesusapp.data.repo

import com.example.jesusapp.data.remote.datasource.MovieListDataSource

class MovieListRepo constructor(val remotesource: MovieListDataSource){
    suspend fun getMoviesList( page:Int)  = remotesource.getMoviesList( page)
    suspend fun getMovieDetails( id:Int ) = remotesource.getMovieDetails(id)
    suspend fun getUsersData() = remotesource.getUsersListData()
    suspend fun getNewsData(page: Int) = remotesource.getNewsListData(page)

}