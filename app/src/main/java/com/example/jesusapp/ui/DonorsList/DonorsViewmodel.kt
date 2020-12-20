package com.example.jesusapp.ui.DonorsList

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.jesusapp.MyApplication
import com.example.jesusapp.data.model.DonarModelItem
import com.example.jesusapp.data.remote.Result
import com.example.jesusapp.data.repo.MovieListRepo
import com.example.jesusapp.db.DonarDao
import com.example.jesusapp.utils.LoadType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DonorsViewmodel @ViewModelInject public constructor(
    val repo: MovieListRepo,
    val donarDao: DonarDao,
    app: Application
) : AndroidViewModel(app) {

    var list = MutableLiveData<ArrayList<DonarModelItem>>()
    var pageNumber: Int = 0;
    var showLoader = MutableLiveData<Boolean>(true)
    var loadType: LoadType = LoadType.INIT
    fun getUserData(page: Int) {
        if (hasInternetConnection()) {
            CoroutineScope(Dispatchers.IO).launch {
                showLoader.postValue(true)
                val result = repo.getUsersData()
                print("hello ${result}")
                when (result.status) {
                    Result.Status.SUCCESS -> {
                        result.data?.let {
                            if (loadType == LoadType.INIT) {
                                // if (it.size == 0)
                                //message.postValue("No Task Found")
                            } else {
                                if (list.value != null) {
                                    list.value!!.addAll(it )
                                }
                            }
                            donarDao.insertDonars(it)
                            list.postValue(it)
                        }
                    }
                    Result.Status.ERROR -> {
                        Log.e("error", result.message.toString());
                    }
                }


            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                showLoader.postValue(true)
                 list.postValue(donarDao.getDonars() as ArrayList<DonarModelItem>)
                Log.e("size", "" + donarDao.getDonars()?.size)
                showLoader.postValue(false)
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
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false

            }
        }
        return false
    }
    fun loadMore() {
        loadType = LoadType.LOADMORE
        getUserData(apiInput())
    }
    private fun apiInput(): Int {
        if (loadType == LoadType.LOADMORE)
            return pageNumber + 10
        else
            return pageNumber

    }
}