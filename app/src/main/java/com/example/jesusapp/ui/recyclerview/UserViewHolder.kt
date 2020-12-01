package com.example.jesusapp.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.R
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.listener.OnItemClickListener
import kotlinx.android.synthetic.main.home_item.view.*


class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    
    fun bindTo(user: Users){
        itemView.txt_tittle.text = user.first_name

        Glide
            .with(itemView)
            .load(user.avatar)
            .centerCrop()
            .into(itemView.img_icon);
    }
}