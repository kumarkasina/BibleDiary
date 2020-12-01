package com.example.jesusapp.listener

public interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}