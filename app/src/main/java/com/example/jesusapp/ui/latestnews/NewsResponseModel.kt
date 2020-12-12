package com.example.jesusapp.ui.latestnews

data class NewsResponseModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)