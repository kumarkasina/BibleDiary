package com.example.jesusapp.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.data.model.PrayerModelItem
import com.example.jesusapp.utils.ConstantValues
import kotlinx.android.synthetic.main.home_item.view.*


class PrayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(user: PrayerModelItem) {
        itemView.txt_tittle.text = user.name

        Glide
            .with(itemView)
            .load(ConstantValues.BASE_URL + user.icon)
            .centerCrop()
            .into(itemView.img_icon)
    }
}