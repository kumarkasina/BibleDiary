package com.example.jesusapp.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jesusapp.utils.ConstantValues


@BindingAdapter("glideImg")
fun glideImg(imageView: ImageView, url: String?) {
    val url = ConstantValues.IMG_PATH+url;
    Glide.with(imageView.context)
        .load(url)
        //.placeholder(R.drawable.ic_video_camera)
        .into(imageView);
}


@BindingAdapter("isShowing")
fun isShowing(view: View, isShowing: Boolean?) {
    view.visibility =if (isShowing == null || isShowing) View.VISIBLE else View.GONE
}

@BindingAdapter("decorator")
fun decorator(recyclerView: RecyclerView, isShowing: Boolean?) {
    recyclerView.addItemDecoration(
        com.example.jesusapp.utils.DividerItemDecoration(recyclerView.context )
    );
}