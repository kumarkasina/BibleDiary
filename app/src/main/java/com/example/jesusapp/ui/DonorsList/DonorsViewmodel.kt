package com.example.jesusapp.ui.DonorsList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jesusapp.data.model.Movie
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.data.remote.Result
import com.example.jesusapp.data.repo.MovieListRepo
import kotlinx.coroutines.launch

class DonorsViewmodel  @ViewModelInject public constructor(val repo: MovieListRepo) : ViewModel() {

    var list = MutableLiveData<List<Users>>()
    var pageNumber:Int = 1;
    var showLoader = MutableLiveData<Boolean>(true)

    fun getUserData(page:Int)
    {
        viewModelScope.launch {
            showLoader.postValue( true)
            val result = repo.getUsersData()

            when (result.status) {
                Result.Status.SUCCESS -> {
                    if(page==1){
                        list.postValue(result.data?.data)
                    }else{
                      //  list.value!!.add( result.data!!.data )
                    }
                   // pageNumber++
                    showLoader.postValue( false)
                }
                Result.Status.ERROR -> {
                }
            }

        }
    }
}