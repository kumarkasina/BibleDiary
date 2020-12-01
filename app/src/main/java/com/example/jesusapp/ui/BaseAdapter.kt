package com.example.jesusapp.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jesusapp.listener.OnItemClickListener

abstract class BaseAdapter<T, U>(var callBackListener: DiffUtil.ItemCallback<T>)
    : ListAdapter<T, BaseAdapter.Holder<T>>(callBackListener) {

    public abstract class Holder<T>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(listener: OnItemClickListener<T>, item: T, adapterPosition: Int)
    }
}