package com.example.jesusapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jesusapp.data.model.Movie
import com.example.jesusapp.data.repo.MovieListRepo
import com.example.jesusapp.data.remote.Result
import kotlinx.coroutines.launch

public class MoviesListViewModel @ViewModelInject public constructor( val repo: MovieListRepo ): ViewModel() {

    var list = MutableLiveData<ArrayList<Movie>>(ArrayList())
    var pageNumber:Int = 1;
    var showLoader = MutableLiveData<Boolean>(true)

    fun getMoviesList(page: Int) {
        viewModelScope.launch {
            showLoader.postValue( true)
            val result = repo.getMoviesList(page)
            when (result.status) {
                Result.Status.SUCCESS -> {
                    if(page==1){
                        list.postValue( result.data!!.results)
                    }else{
                        list.value!!.addAll( result.data!!.results )
                    }
                    pageNumber++
                    showLoader.postValue( false)
                }
                Result.Status.ERROR -> {
                }
            }
        }
    }


}