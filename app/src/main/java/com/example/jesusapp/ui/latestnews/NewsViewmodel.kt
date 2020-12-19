package com.example.jesusapp.ui.latestnews

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jesusapp.data.remote.Result
import com.example.jesusapp.data.repo.MovieListRepo
import com.example.jesusapp.utils.LoadType
import kotlinx.coroutines.launch

class NewsViewmodel @ViewModelInject public constructor(
    app: Application,
    val repo: MovieListRepo
) : AndroidViewModel(app) {

    var list = MutableLiveData<ArrayList<Article>>()
    var pageNumber: Int = 0;
    var showLoader = MutableLiveData<Boolean>(true)
    var loadType: LoadType = LoadType.INIT


    fun getNewsData(input: Int) {
        viewModelScope.launch {
            showLoader.postValue(true)
            Log.e("apiinput", "$input")
            val result = repo.getNewsData(input)

            when (result.status) {
                Result.Status.SUCCESS -> {
                    // list.postValue(result.data?.articles)

                    result.data?.let {
                        if (loadType == LoadType.INIT) {
                            // if (it.size == 0)
                            //message.postValue("No Task Found")
                        } else {
                            // pageNumber++
                            if (list.value != null) {
                                it.addAll(0, list.value!!)
                            }

                        }
                        list.postValue(it)
                    }


                }
                else -> {
                    Log.e("faliled", "====")
                }

            }
            showLoader.postValue(false)
        }
    }

    fun initApiCall() {
        loadType = LoadType.INIT;
        pageNumber = 0;
        getNewsData(apiInput())
    }

    private fun apiInput(): Int {
        if (loadType == LoadType.LOADMORE)
            return pageNumber + 10
        else
            return pageNumber

    }

    fun loadMore() {
        loadType = LoadType.LOADMORE
        getNewsData(apiInput())
    }

}