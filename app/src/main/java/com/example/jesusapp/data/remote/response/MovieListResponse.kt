package com.example.jesusapp.data.remote.response

import com.example.jesusapp.data.ApiResponse
import com.example.jesusapp.data.model.Movie

class MovieListResponse(
    val page: Int,
    val results: ArrayList<Movie>,
    val total_pages: Int,
    val total_results: Int
): ApiResponse()
