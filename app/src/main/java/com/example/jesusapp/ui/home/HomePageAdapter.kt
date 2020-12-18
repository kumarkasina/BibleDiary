package com.example.jesusapp.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.jesusapp.R
import com.example.jesusapp.data.model.HomeDataModel1Item
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.recyclerview.FeatureItemDiffCallback
import com.example.jesusapp.ui.recyclerview.FeatureViewHolder
import kotlinx.android.synthetic.main.home_item.view.*

public class HomePageAdapter(
    onItemClickListener: OnItemClickListener<HomeDataModel1Item>,
    type: Int
) : ListAdapter<HomeDataModel1Item, FeatureViewHolder>(
    FeatureItemDiffCallback()
) {
    val onItemClickListener: OnItemClickListener<HomeDataModel1Item> = onItemClickListener
    val type: Int = type
    var lastpos: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        if (type == 0)
            return FeatureViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_item, parent, false)
            )
        else
            return FeatureViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.donor_item, parent, false)
            )

    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        holder.bindTo(getItem(position))
        if (type == 0)
            if (getItem(position).selected) {
                holder.itemView.container.isSelected = true
            } else {
                holder.itemView.container.isSelected = false
            }

        holder.itemView.setOnClickListener(View.OnClickListener {

            onItemClickListener.onItemClick(getItem(position), position)
        })


    }

}