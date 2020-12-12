package com.example.jesusapp.ui.latestnews

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.jesusapp.data.remote.Result
import com.example.jesusapp.data.repo.MovieListRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewmodel @ViewModelInject public constructor(
    app: Application,
    val repo: MovieListRepo
) : AndroidViewModel(app) {

    var list = MutableLiveData<List<Article>>()
    var pageNumber: Int = 1;
    var showLoader = MutableLiveData<Boolean>(true)


    fun getNewsData() {
        CoroutineScope(Dispatchers.IO).launch {
            showLoader.postValue(true)
            val result = repo.getNewsData()

            when (result.status) {
                Result.Status.SUCCESS -> {
                    list.postValue(result.data?.articles)
                }
                else -> {
                    Log.e("faliled", "====")
                }

            }
            showLoader.postValue(false)
        }
    }

}