package com.example.jesusapp.ui.HomeDetail

import androidx.recyclerview.widget.DiffUtil
import com.example.jesusapp.data.model.PrayerDetailModelsItem


class PrayerItemDiffCallback : DiffUtil.ItemCallback<PrayerDetailModelsItem>() {

    override fun areItemsTheSame(
        oldItem: PrayerDetailModelsItem,
        newItem: PrayerDetailModelsItem
    ): Boolean = oldItem.prayer_id == newItem.prayer_id

    override fun areContentsTheSame(
        oldItem: PrayerDetailModelsItem,
        newItem: PrayerDetailModelsItem
    ): Boolean = oldItem == newItem
}