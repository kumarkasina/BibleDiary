package com.example.jesusapp.ui.programs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.R
import com.example.jesusapp.utils.ConstantValues
import kotlinx.android.synthetic.main.donor_item.view.img_icon
import kotlinx.android.synthetic.main.donor_item.view.txt_tittle
import kotlinx.android.synthetic.main.news_item.view.*

class ProgramAdapter : RecyclerView.Adapter<ProgramAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<ProgramResponseModelItem>() {
        override fun areItemsTheSame(
            oldItem: ProgramResponseModelItem,
            newItem: ProgramResponseModelItem
        ): Boolean =
            oldItem.program_id == newItem.program_id

        override fun areContentsTheSame(
            oldItem: ProgramResponseModelItem,
            newItem: ProgramResponseModelItem
        ): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)
    private var onBottomReachedListener: OnBottomReachedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        var article = differ.currentList[position]

        if (position == itemCount - 1) {
            onBottomReachedListener?.onBottomReached(position)
        }


        holder.itemView.apply {
            Glide.with(this)
                .load(ConstantValues.BASE_URL + article.image)
                .into(img_icon)
            txt_tittle.text = "Topic: " + article.topic_name
            txt_desc.text = article.description

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnBottomReachedListener {
        fun onBottomReached(position: Int)
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener?) {
        this.onBottomReachedListener = onBottomReachedListener
    }


}