package com.example.jesusapp.ui.programs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jesusapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.header.*

@AndroidEntryPoint
class ProgramActivity : AppCompatActivity(), ProgramAdapter.OnBottomReachedListener {

    lateinit var adapter: ProgramAdapter
    val viewmodel: ProgramViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program)

        initData()

        home.setOnClickListener { finish() }

        viewmodel.showLoader.observe(this, {
            Log.e("it", "$it")
            if (it) {
                progressBar1.visibility = View.VISIBLE
            } else {
                progressBar1.visibility = View.GONE
            }
        })

    }

    private fun initData() {
        adapter = ProgramAdapter()
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