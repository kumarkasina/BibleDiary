package com.example.jesusapp.ui.DonorsList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jesusapp.R
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_donors.*


@AndroidEntryPoint
class DonorsActivity : AppCompatActivity(), OnItemClickListener<Users> {

    lateinit var adapter : HomeAdapter
    val donorsViewmodel: DonorsViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donors)

        initData()
    }

    private fun initData() {

        adapter = HomeAdapter(this,1)
        donors_recycler.adapter = adapter
        donors_recycler.layoutManager = LinearLayoutManager(this)
        donorsViewmodel.pageNumber=1
        donorsViewmodel.getUserData(page = donorsViewmodel.pageNumber)
        donorsViewmodel.list.observe(this,{
            adapter.submitList(it)
        })

    }

    override fun onItemClick(item: Users, position: Int) {

    }
}