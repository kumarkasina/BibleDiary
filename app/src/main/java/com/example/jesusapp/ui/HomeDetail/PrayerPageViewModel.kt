package com.example.jesusapp.ui.HomeDetail

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
import com.example.jesusapp.data.model.PrayerModelItem
import com.example.jesusapp.data.remote.Apis
import com.example.jesusapp.data.remote.MainActivityViewState
import com.example.jesusapp.db.PrayerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrayerPageViewModel @ViewModelInject constructor(
    val apis: Apis,
    val dao: PrayerDao,
    app: Application
) : AndroidViewModel(app) {

    private val _state: MutableLiveData<MainActivityViewState> = MutableLiveData()
    val state: LiveData<MainActivityViewState> = _state

    var list = MutableLiveData<List<PrayerModelItem>>()


    init {
        getData()
    }

    private fun getData() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                if (hasInternetConnection()) {
                    flowOf(apis.getPrayersData()).catch { throwable ->
                        _state.postValue(
                            MainActivityViewState.ShowError(
                                throwable
                            )
                        )
                    }.map { result ->
                        if (!result.body().isNullOrEmpty()) {
                            dao.deleteAllPrayers()
                            result?.body()?.toList()?.let { dao.insertPrayers(it) }
                        }
                    }.collect()
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