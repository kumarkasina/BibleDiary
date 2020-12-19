package com.example.jesusapp.ui.home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jesusapp.MyApplication
import com.example.jesusapp.data.model.HomeDataModel1Item


import com.example.jesusapp.data.remote.Apis
import com.example.jesusapp.data.remote.MainActivityViewState
import com.example.jesusapp.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageViewModel @ViewModelInject constructor(
    val apis: Apis,
    val userDao: UserDao, app: Application
) : AndroidViewModel(app) {

    private val _state: MutableLiveData<MainActivityViewState> = MutableLiveData()
    val state: LiveData<MainActivityViewState> = _state

    var list = MutableLiveData<List<HomeDataModel1Item>>()
    var pageno: Int = 0;

    init {
        _state.postValue(MainActivityViewState.ShowLoading)
        getHomePageData()
    }

    private fun getHomePageData() {

        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                if (pageno == 1) {
                    if (hasInternetConnection()) {
                        flowOf(apis.getHomePageData()).catch { throwable ->
                            _state.postValue(
                                MainActivityViewState.ShowError(
                                    throwable
                                )
                            )
                        }.map { result ->
                            if (!result.body().isNullOrEmpty()) {
                                userDao.deleteAllFeatures()
                                result?.body()?.toList()?.let { userDao.insertHomePag1(it) }

                            }
                        }.collect()
                    }
                } else {
                    flowOf(userDao.get2AllFeatures()).collect()
                }


            }
        }

    }

    fun hasInternetConnection(): Boolean {

        val connectivitManager =
            getApplication<MyApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivitManager.activeNetwork ?: return false
            val capabilities =
                connectivitManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false

            }
        }
        return false
    }


}