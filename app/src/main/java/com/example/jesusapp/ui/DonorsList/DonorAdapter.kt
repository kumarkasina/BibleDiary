package com.example.jesusapp.ui.DonorsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.jesusapp.R
import com.example.jesusapp.data.model.DonarModelItem
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.recyclerview.DoanrItemDiffCallback
import com.example.jesusapp.ui.recyclerview.DonarViewHolder

class DonarAdapter(onItemClickListener: OnItemClickListener<DonarModelItem>) : ListAdapter<DonarModelItem, DonarViewHolder>(
    DoanrItemDiffCallback()
){
    val  onItemClickListener: OnItemClickListener<DonarModelItem> = onItemClickListener
    var lastpos:Int = -1
    private var onBottomReachedListener:OnBottomReachedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonarViewHolder {

            return DonarViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.donor_item, parent, false)
            )

    }

    override fun onBindViewHolder(holder: DonarViewHolder, position: Int) {
        if (position == itemCount - 1) {
            onBottomReachedListener?.onBottomReached(position)
        }
        holder.bindTo(getItem(position))

        holder.itemView.setOnClickListener(View.OnClickListener {

            onItemClickListener.onItemClick(getItem(position), position)
        })

        /*holder.itemView.container.setOnClickListener(View.OnClickListener {

            if (lastpos>=0){
                getItem(lastpos).selected=false
            }
            getItem(position).selected=true

            lastpos=position

            if(getItem(position).selected){
                it.isSelected=true
            }else
                it.isSelected=false

            notifyDataSetChanged()

        })*/


    }
    interface OnBottomReachedListener  {
        fun onBottomReached(position: Int)
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener?) {
        this.onBottomReachedListener = onBottomReachedListener
    }

}