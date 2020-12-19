package com.example.jesusapp.ui.latestnews

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(

    @SerializedName("topic_name")
    @Expose
    val title: String,
    @SerializedName("news_id")
    @Expose
    val url: Int,
    @SerializedName("image")
    @Expose
    val urlToImage: String,
    val description: String
)

/*
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)*/
