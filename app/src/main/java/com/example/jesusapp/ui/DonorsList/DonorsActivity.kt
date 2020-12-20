package com.example.jesusapp.ui.DonorsList

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jesusapp.R
import com.example.jesusapp.data.model.DonarModelItem
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_donors.*


@AndroidEntryPoint
class DonorsActivity : AppCompatActivity(), OnItemClickListener<DonarModelItem>,DonarAdapter.OnBottomReachedListener {

    lateinit var adapter : DonarAdapter
    val donorsViewmodel: DonorsViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donors)

        initData()
    }

    private fun initData() {

        adapter = DonarAdapter(this)
        donors_recycler.adapter = adapter
        donors_recycler.layoutManager = LinearLayoutManager(this)
        donorsViewmodel.pageNumber=1
        donorsViewmodel.getUserData(page = donorsViewmodel.pageNumber)
        donorsViewmodel.list.observe(this,{
            adapter.submitList(it)
        })

    }

    override fun onItemClick(item: DonarModelItem, position: Int) {
    }
    override fun onBottomReached(position: Int) {
        Log.e("bootom reached at $position", "sad")
        if (!donorsViewmodel.showLoader.value!!)
            donorsViewmodel.loadMore()
    }
}