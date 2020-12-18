package com.example.jesusapp.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.jesusapp.data.model.PrayerModelItem


class PrayerItemDiffCallback : DiffUtil.ItemCallback<PrayerModelItem>() {

    override fun areItemsTheSame(oldItem: PrayerModelItem, newItem: PrayerModelItem): Boolean =
        oldItem.cat_id == newItem.cat_id

    override fun areContentsTheSame(oldItem: PrayerModelItem, newItem: PrayerModelItem): Boolean =
        oldItem == newItem
}