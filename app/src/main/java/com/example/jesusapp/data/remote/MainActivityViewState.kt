package com.example.jesusapp.data.remote

import com.example.jesusapp.data.model.Users


sealed class MainActivityViewState {
    object ShowLoading : MainActivityViewState()
    class ShowError(val error: Throwable) : MainActivityViewState()
    class ShowData(val data: List<Users>) : MainActivityViewState()
}