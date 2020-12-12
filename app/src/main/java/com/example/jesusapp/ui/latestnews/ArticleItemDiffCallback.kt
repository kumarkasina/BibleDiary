package com.example.jesusapp.ui.latestnews

import androidx.recyclerview.widget.DiffUtil


class ArticleItemDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem == newItem
}