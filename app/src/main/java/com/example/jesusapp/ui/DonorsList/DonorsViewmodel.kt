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
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.data.remote.Result
import com.example.jesusapp.data.repo.MovieListRepo
import com.example.jesusapp.db.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DonorsViewmodel @ViewModelInject public constructor(
    val repo: MovieListRepo,
    val userDao: UserDao,
    app: Application
) : AndroidViewModel(app) {

    var list = MutableLiveData<List<Users>>()
    var pageNumber: Int = 1;
    var showLoader = MutableLiveData<Boolean>(true)

    fun getUserData(page: Int) {
        if (hasInternetConnection()) {
            CoroutineScope(Dispatchers.IO).launch {
                showLoader.postValue(true)
                val result = repo.getUsersData()

                when (result.status) {
                    Result.Status.SUCCESS -> {
                        if (page == 1) {

                            result.data?.data?.let { userDao.insertUsers(it) }

                            list.postValue(result.data?.data)
                        } else {
                            //  list.value!!.add( result.data!!.data )
                        }
                        // pageNumber++
                        showLoader.postValue(false)
                    }
                    Result.Status.ERROR -> {
                    }
                }


            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                Log.e("internet", "no");
                showLoader.postValue(true)
                list.postValue(userDao.getAllUsers2())
                Log.e("size", "" + userDao.getAllUsers2()?.size)
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
}