package com.example.jesusapp.ui.latestnews

data class NewsResponseModel(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)