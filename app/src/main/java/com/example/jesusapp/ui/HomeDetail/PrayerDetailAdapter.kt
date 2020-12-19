package com.example.jesusapp.ui.HomeDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.jesusapp.R
import com.example.jesusapp.data.model.PrayerDetailModelsItem
import com.example.jesusapp.listener.OnExpandListener
import kotlinx.android.synthetic.main.row_prayer.view.*

class PrayerDetailAdapter(onexpandlistener: OnExpandListener<PrayerDetailModelsItem>) :
    ListAdapter<PrayerDetailModelsItem,
            PrayerDetailViewHolder>(PrayerItemDiffCallback()) {

    val onexpandlistener: OnExpandListener<PrayerDetailModelsItem> = onexpandlistener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerDetailViewHolder {
        return PrayerDetailViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_prayer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PrayerDetailViewHolder, position: Int) {
        holder.bindTo(getItem(position))
        holder.itemView.row.isSelected=true

        if(getItem(position).expanded){

            holder.itemView.img_arrow.setImageResource(R.drawable.down_arrow)
            holder.itemView.underlayout.visibility=View.VISIBLE
        }else
        {
            holder.itemView.img_arrow.setImageResource(R.drawable.up_arrow)
            holder.itemView.underlayout.visibility=View.GONE

        }



        holder.itemView.setOnClickListener(View.OnClickListener {
            var prayerDetailModel: PrayerDetailModelsItem = getItem(position)


            if (getItem(position).expanded) {
                prayerDetailModel.expanded = false
                holder.itemView.img_arrow.setImageResource(R.drawable.up_arrow)
                holder.itemView.underlayout.visibility = View.GONE
            } else {
                prayerDetailModel.expanded = true
                holder.itemView.img_arrow.setImageResource(R.drawable.down_arrow)
                holder.itemView.underlayout.visibility = View.VISIBLE

            }
            notifyItemChanged(position)
        })
    }
}