package com.example.jesusapp.data.remote

import com.example.jesusapp.data.model.DataModel
import com.example.jesusapp.data.remote.response.MovieData
import com.example.jesusapp.data.remote.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Apis {

    @GET("/3/movie/popular?api_key=a0525b2df20e02083f3fa151f5819558")
    suspend fun getMoviesList( @Query("page") page:Int) : Response<MovieListResponse>

    @GET("/3/movie/{movie_id}?api_key=a0525b2df20e02083f3fa151f5819558")
    suspend fun getMovieDetails( @Path("movie_id") movie_id:Int) : Response<MovieData>

    @GET("/api/users?page=2")
    suspend fun getData() : DataModel

    @GET("/api/users?page=1")
    suspend fun getDatas() : Response<DataModel>




}