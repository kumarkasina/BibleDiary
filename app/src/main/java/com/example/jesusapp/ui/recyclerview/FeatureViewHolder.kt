package com.example.jesusapp.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.data.model.HomeDataModel1Item
import com.example.jesusapp.utils.ConstantValues

import kotlinx.android.synthetic.main.home_item.view.*


class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(user: HomeDataModel1Item) {
        itemView.txt_tittle.text = user.feature_name

        Glide
            .with(itemView)
            .load(ConstantValues.BASE_URL + user.image)
            .centerCrop()
            .into(itemView.img_icon);
    }
}