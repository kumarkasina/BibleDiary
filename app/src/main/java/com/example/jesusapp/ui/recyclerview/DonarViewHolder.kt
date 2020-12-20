package com.example.jesusapp.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.data.model.DonarModelItem
import com.example.jesusapp.utils.ConstantValues
import kotlinx.android.synthetic.main.donor_item.view.*

import kotlinx.android.synthetic.main.home_item.view.*
import kotlinx.android.synthetic.main.home_item.view.img_icon
import kotlinx.android.synthetic.main.home_item.view.txt_tittle


class DonarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindTo(user: DonarModelItem){

        itemView.txt_tittle.text = user.donor_name
        itemView.txt_addres.text=user.address
        itemView.txt_mobile.text=user.mobile_no
        itemView.txt_state.text=user.state
        itemView.txt_country.text=user.country
        itemView.txt_designation.text=user.designation
        Glide
            .with(itemView)
            .load(ConstantValues.BASE_URL2+user.image)
            .centerCrop()
            .into(itemView.img_icon);
    }

}

class DoanrItemDiffCallback : DiffUtil.ItemCallback<DonarModelItem>() {

    override fun areItemsTheSame
                (oldItem: DonarModelItem, newItem: DonarModelItem): Boolean
            = oldItem.donor_id == newItem.donor_id

    override fun areContentsTheSame
                (oldItem: DonarModelItem, newItem: DonarModelItem): Boolean
            = oldItem == newItem
}