package com.example.jesusapp.listener

public interface OnExpandListener<T> {
    fun onExpand(item: T, position: Int)
}