package com.example.jesusapp.ui.latestnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.R
import kotlinx.android.synthetic.main.donor_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.donor_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        var article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage)
                .into(img_icon)
            txt_tittle.text = article.title

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}