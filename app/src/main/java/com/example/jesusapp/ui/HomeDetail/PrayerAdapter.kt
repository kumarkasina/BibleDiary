package com.example.jesusapp.ui.HomeDetail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.jesusapp.R
import com.example.jesusapp.data.model.PrayerModelItem
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.recyclerview.PrayerItemDiffCallback
import com.example.jesusapp.ui.recyclerview.PrayerViewHolder
import kotlinx.android.synthetic.main.horizontal_item.view.*


class PrayerAdapter(onItemClickListener: OnItemClickListener<PrayerModelItem>, type: Int) :
    ListAdapter<PrayerModelItem, PrayerViewHolder>(
        PrayerItemDiffCallback()
    ) {
    val onItemClickListener: OnItemClickListener<PrayerModelItem> = onItemClickListener
    val type: Int = type
    var lastpos: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerViewHolder {


        return PrayerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.horizontal_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: PrayerViewHolder, position: Int) {
        holder.bindTo(getItem(position))

        if (getItem(position).selected) {
            holder.itemView.container.isSelected = true
            holder.itemView.txt_tittle.setTextColor(Color.parseColor("#250833"))
        } else {
            holder.itemView.container.isSelected = false
            holder.itemView.txt_tittle.setTextColor(Color.parseColor("#6BB3AC"))
        }



        holder.itemView.container.setOnClickListener(View.OnClickListener {

            if (lastpos >= 0) {
                getItem(lastpos).selected = false
            }
            getItem(position).selected = true

            lastpos = position

            if (getItem(position).selected) {
                it.isSelected = true
                holder.itemView.txt_tittle.setTextColor(Color.parseColor("#250833"))
            } else
                it.isSelected = false
            holder.itemView.txt_tittle.setTextColor(Color.parseColor("#6BB3AC"))

            onItemClickListener.onItemClick(getItem(position), position)

            notifyDataSetChanged()

        })


    }

}