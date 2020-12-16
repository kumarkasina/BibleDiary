package com.example.jesusapp.ui.latestnews

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jesusapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*

@AndroidEntryPoint
class NewsActivity : AppCompatActivity(), NewsAdapter.OnBottomReachedListener {

    lateinit var adapter: NewsAdapter
    val viewmodel: NewsViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        initData()

        viewmodel.showLoader.observe(this, {
            Log.e("it", "$it")
            if (it) {
                progressBar1.visibility = VISIBLE
            } else {
                progressBar1.visibility = GONE
            }
        })

    }

    private fun initData() {
        adapter = NewsAdapter()
        recy_news.adapter = adapter
        recy_news.layoutManager = LinearLayoutManager(this)
        adapter.setOnBottomReachedListener(this)
        viewmodel.initApiCall()
        viewmodel.list.observe(this, {
            adapter.differ.submitList(it)
            Log.e("listsize", "" + it.size)
        })
    }

    override fun onBottomReached(position: Int) {
        Log.e("bootom reached at $position", "sad")
        if (!viewmodel.showLoader.value!!)
            viewmodel.loadMore()
    }
}