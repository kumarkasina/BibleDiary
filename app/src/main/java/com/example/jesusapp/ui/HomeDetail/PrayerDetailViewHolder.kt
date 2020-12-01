package com.example.jesusapp.ui.HomeDetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.R
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.listener.OnItemClickListener
import kotlinx.android.synthetic.main.home_item.view.*
import kotlinx.android.synthetic.main.row_prayer.view.*


class PrayerDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    
    fun bindTo(user: PrayerDetailModel){

        itemView.txt_heading.text = user.heading
        itemView.txt_subheading.text = user.subheading

    }
}