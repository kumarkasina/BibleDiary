
package com.example.jesusapp.ui.home

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.jesusapp.R

import com.squareup.picasso.Picasso

class MovieFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
  Bundle?): View? {

    // Creates the view controlled by the fragment
    val view = inflater.inflate(R.layout.fragment_movie, container, false)
    val titleTextView = view.findViewById<TextView>(R.id.titleTextView)

    val posterImageView = view.findViewById<ImageView>(R.id.posterImageView)


    // Retrieve and display the movie data from the Bundle
    val args = arguments
    if (args != null) {
      titleTextView.text = args.getString(MovieHelper.KEY_TITLE)
    }


    // Download the image and display it using Picasso
   /* Picasso.with(activity)
        .load(resources.getIdentifier(args?.getString(MovieHelper.KEY_POSTER_URI), "drawable", activity?.packageName))
        .into(posterImageView)*/

    return view
  }

  companion object {

    // Method for creating new instances of the fragment
    fun newInstance(movie: Movie): MovieFragment {

      // Store the movie data in a Bundle object
      val args = Bundle()
      args.putString(MovieHelper.KEY_TITLE, movie.title)
      args.putString(MovieHelper.KEY_POSTER_URI, movie.posterUri)


      // Create a new MovieFragment and set the Bundle as the arguments
      // to be retrieved and displayed when the view is created
      val fragment = MovieFragment()
      fragment.arguments = args
      return fragment
    }
  }

}
