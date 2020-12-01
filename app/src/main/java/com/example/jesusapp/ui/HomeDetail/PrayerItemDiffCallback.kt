package com.example.jesusapp.ui.HomeDetail

import androidx.recyclerview.widget.DiffUtil
import com.example.jesusapp.data.model.Users


class PrayerItemDiffCallback : DiffUtil.ItemCallback<PrayerDetailModel>() {

    override fun areItemsTheSame
                (oldItem: PrayerDetailModel, newItem: PrayerDetailModel): Boolean
            = oldItem.heading == newItem.heading

    override fun areContentsTheSame
                (oldItem: PrayerDetailModel, newItem: PrayerDetailModel): Boolean
            = oldItem == newItem
}