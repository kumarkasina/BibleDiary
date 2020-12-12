package com.example.jesusapp.ui.latestnews

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jesusapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    val viewmodel: NewsViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        initData()

    }

    private fun initData() {
        adapter = NewsAdapter()
        recy_news.adapter = adapter
        recy_news.layoutManager = LinearLayoutManager(this)
        viewmodel.getNewsData()
        viewmodel.list.observe(this, {
            adapter.differ.submitList(it)
            Log.e("listsize", "" + it.size)
        })
    }
}