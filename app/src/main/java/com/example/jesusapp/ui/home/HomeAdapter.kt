package com.example.jesusapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.jesusapp.R
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.recyclerview.UserItemDiffCallback
import com.example.jesusapp.ui.recyclerview.UserViewHolder
import kotlinx.android.synthetic.main.home_item.view.*

class HomeAdapter(onItemClickListener: OnItemClickListener<Users>, type: Int) : ListAdapter<Users, UserViewHolder>(
    UserItemDiffCallback()
){
     val  onItemClickListener: OnItemClickListener<Users> = onItemClickListener
     val type:Int=type
    var lastpos:Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        if(type==0)
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_item, parent, false)
        )
        else
            return UserViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.donor_item, parent, false)
            )

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(getItem(position))
        if(type==0)
        if(getItem(position).selected){
            holder.itemView.container.isSelected=true
        }else{
            holder.itemView.container.isSelected=false
        }

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

}