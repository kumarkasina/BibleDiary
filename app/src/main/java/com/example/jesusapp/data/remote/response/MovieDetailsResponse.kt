package com.example.jesusapp.data.remote.response

import com.example.jesusapp.data.ApiResponse

class MovieData (
    val id : Int,
    val poster_path : String,
    val release_date : String,
    val title : String,
): ApiResponse()