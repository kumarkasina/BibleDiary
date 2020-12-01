package com.example.jesusapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jesusapp.data.remote.Apis
import com.example.jesusapp.data.remote.MainActivityViewState
import com.example.jesusapp.db.UserDao

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


class SharedHomeViewModel  @ViewModelInject constructor(
private val apis: Apis,
public val userDao: UserDao
): ViewModel(

) {

    private val _state : MutableLiveData<MainActivityViewState> = MutableLiveData()
    val state: LiveData<MainActivityViewState> = _state


    init {
        _state.postValue(MainActivityViewState.ShowLoading)
        getData()
    }


   private fun getData(){

       viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                flowOf(apis.getData()).catch {
                        throwable ->
                    _state.postValue(MainActivityViewState.ShowError(
                        throwable)
                    )
                }.map { result->
                    if(!result.data.isNullOrEmpty()){
                        userDao.deleteAllUsers()
                        userDao.insertUsers(result.data)
                    }
                }.collect()



            }
       }

   }


}