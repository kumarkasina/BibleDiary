package com.example.jesusapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.jesusapp.data.model.Movie
import com.example.jesusapp.databinding.ItemMovieBinding
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.BaseAdapter

class MovieListAdapter(var listener: OnItemClickListener<Movie>) :
    BaseAdapter<Movie, BaseAdapter.Holder<Movie>>(DiffCallBack()) {

    class DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private var onBottomReachedListener: OnBottomReachedListener? = null

    class ViewHolder(val binding: ItemMovieBinding) : BaseAdapter.Holder<Movie>(binding) {
        override fun bind(listener: OnItemClickListener<Movie>, item: Movie,
                          adapterPosition: Int) {
            binding.movie = item
            binding.listener = listener
            binding.position = adapterPosition
        }
    }

    interface OnBottomReachedListener {
        fun onBottomReached(position: Int)
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener?) {
        this.onBottomReachedListener = onBottomReachedListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<Movie> {
        return ViewHolder(ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: Holder<Movie>, position: Int) {
        if (position == itemCount - 1) {
            onBottomReachedListener?.onBottomReached(position)
        }
        val item = getItem(position)
        holder.bind(listener, item, position)
    }

}