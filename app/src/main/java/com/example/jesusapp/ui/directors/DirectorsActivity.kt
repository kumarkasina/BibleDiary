package com.example.jesusapp.ui.directors

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jesusapp.R
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.db.UserDao
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.DonorsList.DonorsViewmodel
import com.example.jesusapp.ui.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_directors.*
import javax.inject.Inject

@AndroidEntryPoint
class DirectorsActivity : AppCompatActivity(), OnItemClickListener<Users> {


    val donorsViewmodel: DonorsViewmodel by viewModels()
    lateinit var adapter: HomeAdapter

    @Inject
    lateinit var usersDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directors)

        initData()

    }

    private fun initData() {

        adapter = HomeAdapter(this, 0)
        menu_list.adapter = adapter
        menu_list.layoutManager = GridLayoutManager(this, 2)
        menu_list.setHasFixedSize(true)
        donorsViewmodel.pageNumber = 1
        donorsViewmodel.getUserData(page = donorsViewmodel.pageNumber)
        donorsViewmodel.list.observe(this, {
            adapter.submitList(it)
        })

    }

    override fun onItemClick(item: Users, position: Int) {


        val intent = Intent(this, ShowDirector::class.java).apply {
            putExtra("name", item.first_name)
            putExtra("imageurl", item.avatar)
        }

        startActivity(intent)

    }
}