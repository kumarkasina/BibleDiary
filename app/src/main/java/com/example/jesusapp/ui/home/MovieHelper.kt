

package com.example.jesusapp.ui.home

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object MovieHelper {

  val KEY_TITLE = "title"
  val KEY_POSTER_URI = "posterUri"

  fun getMoviesFromJson(fileName: String, context: Context): ArrayList<Movie> {

    val movies = ArrayList<Movie>()

    try {
      // Load the JSONArray from the file
      val jsonString = loadJsonFromFile(fileName, context)
      val json = JSONObject(jsonString)
      val jsonMovies = json.getJSONArray("movies")

      // Create the list of Movies
      for (index in 0 until jsonMovies.length()) {
        val movieTitle = jsonMovies.getJSONObject(index).getString(KEY_TITLE)
        val moviePosterUri = jsonMovies.getJSONObject(index).getString(KEY_POSTER_URI)
        movies.add(Movie(movieTitle, moviePosterUri))
      }
    } catch (e: JSONException) {
      return movies
    }

    return movies
  }

  private fun loadJsonFromFile(filename: String, context: Context): String {
    var json = ""

    try {
      val input = context.assets.open(filename)
      val size = input.available()
      val buffer = ByteArray(size)
      input.read(buffer)
      input.close()
      json = buffer.toString(Charsets.UTF_8)
    } catch (e: IOException) {
      e.printStackTrace()
    }

    return json
  }
}