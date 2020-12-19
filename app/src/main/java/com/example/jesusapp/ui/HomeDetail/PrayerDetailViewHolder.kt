package com.example.jesusapp.ui.HomeDetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.jesusapp.data.model.PrayerDetailModelsItem
import kotlinx.android.synthetic.main.row_prayer.view.*


class PrayerDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindTo(user: PrayerDetailModelsItem) {

        itemView.txt_heading.text = user.prayer_type
        itemView.txt_subheading.text = user.message

    }
}