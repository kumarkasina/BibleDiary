package com.example.jesusapp.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.jesusapp.data.model.DairyCategoriesModelItem


class DiaryItemDiffCallback : DiffUtil.ItemCallback<DairyCategoriesModelItem>() {

    override fun areItemsTheSame(
        oldItem: DairyCategoriesModelItem,
        newItem: DairyCategoriesModelItem
    ): Boolean = oldItem.cat_id == newItem.cat_id

    override fun areContentsTheSame(
        oldItem: DairyCategoriesModelItem,
        newItem: DairyCategoriesModelItem
    ): Boolean = oldItem == newItem
}