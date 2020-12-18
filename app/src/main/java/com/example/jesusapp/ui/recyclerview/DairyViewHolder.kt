package com.example.jesusapp.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.data.model.DairyCategoriesModelItem
import kotlinx.android.synthetic.main.home_item.view.*


class DairyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(user: DairyCategoriesModelItem) {
        itemView.txt_tittle.text = user.name

        Glide
            .with(itemView)
            .load("https://7873fc0cc37c.ngrok.io/uploads//3c2f05092579dc13d5efaeafe4623f44.jpg")
            .centerCrop()
            .into(itemView.img_icon);
    }
}