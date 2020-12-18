package com.example.jesusapp.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.jesusapp.data.model.HomeDataModel1Item


class FeatureItemDiffCallback : DiffUtil.ItemCallback<HomeDataModel1Item>() {

    override fun areItemsTheSame(
        oldItem: HomeDataModel1Item,
        newItem: HomeDataModel1Item
    ): Boolean = oldItem.feature_id == newItem.feature_id

    override fun areContentsTheSame(
        oldItem: HomeDataModel1Item,
        newItem: HomeDataModel1Item
    ): Boolean = oldItem == newItem
}