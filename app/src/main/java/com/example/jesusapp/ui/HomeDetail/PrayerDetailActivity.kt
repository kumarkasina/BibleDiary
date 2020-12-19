package com.example.jesusapp.ui.HomeDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jesusapp.R
import com.example.jesusapp.data.model.PrayerDetailModelsItem
import com.example.jesusapp.data.model.PrayerModelItem
import com.example.jesusapp.data.remote.MainActivityViewState
import com.example.jesusapp.db.PrayerDao
import com.example.jesusapp.listener.OnExpandListener
import com.example.jesusapp.listener.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_prayer_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PrayerDetailActivity : AppCompatActivity(), OnItemClickListener<PrayerModelItem>,
    OnExpandListener<PrayerDetailModelsItem> {


    @Inject
    lateinit var usersDao: PrayerDao
    lateinit var adapter: PrayerAdapter
    lateinit var prayeradapter: PrayerDetailAdapter
    lateinit var datas: ArrayList<PrayerDetailModel>

    val viewModel: PrayerPageViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_prayer_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initialUiState()
        observeViewState()
        observeUsersInDatabase()
        initrecylcerview()
        //setData()
    }

    /* private fun setData() {
         datas= ArrayList()
        var prayerDetailModel = PrayerDetailModel("1","బిగ్ బాస్",false)
         datas.add(prayerDetailModel)
         var prayerDetailModel1 = PrayerDetailModel("2","నటుల మధ్య చిచ్చుపెట్టిన గ్రేటర్ పోరు",false)
         datas.add(prayerDetailModel1)
         var prayerDetailModel2 = PrayerDetailModel("3","ఆ మూడు తప్పిదాలతోనే టీమిండియా మూల్యం!",false)
         datas.add(prayerDetailModel2)
         var prayerDetailModel3 = PrayerDetailModel("4","ఏడుపాయల క్షేత్రంలో చోరీ",false)
         datas.add(prayerDetailModel3)
         var prayerDetailMode4 = PrayerDetailModel("5","బగెలిపిస్తే హైదరాబాద్ పేరు మారుస్తాం : యోగ",false)
         datas.add(prayerDetailMode4)
         prayeradapter.submitList(datas)
         var prayerDetailModel5 = PrayerDetailModel("6","ఆ మూడు తప్పిదాలతోనే టీమిండియా మూల్యం!",false)
         datas.add(prayerDetailModel5)
         var prayerDetailModel6 = PrayerDetailModel("7","ఏడుపాయల క్షేత్రంలో చోరీ",false)
         datas.add(prayerDetailModel6)
         var prayerDetailMode7 = PrayerDetailModel("8","బగెలిపిస్తే హైదరాబాద్ పేరు మారుస్తాం : యోగ",false)
         datas.add(prayerDetailMode7)
         prayeradapter.submitList(datas)

     }*/

    private fun initrecylcerview() {
        prayeradapter = PrayerDetailAdapter(this)
        vertical_recyle.adapter=prayeradapter
        vertical_recyle.layoutManager = LinearLayoutManager(this)
        vertical_recyle.setHasFixedSize(true)
    }

    private fun  observeViewState()
    {

            viewModel.state.observe(this, Observer { state ->
                when (state) {
                    is MainActivityViewState.ShowLoading -> {


                    }
                    is MainActivityViewState.ShowError -> {

                    }
                }
            })


    }
    private fun initialUiState() {

        adapter = PrayerAdapter(this, 1)
        horizontal_recycler.adapter = adapter
        horizontal_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)


    }


    fun observeUsersInDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            usersDao.getAllPrayerDistinctUntilChanged().collect { users ->
                showData(users)
                Log.e("count", "1")
            }
        }
    }

    private fun showData(data: List<PrayerModelItem>) {
        Log.e("size", "${data.size}")
        horizontal_recycler.visibility = View.VISIBLE
        adapter.submitList(data)
    }


    override fun onItemClick(item: PrayerModelItem, position: Int) {
        Log.e("cli", "${item.prayer.size}")
        txt_heading.setText(item.name)
        prayeradapter.submitList(item.prayer)
    }

    override fun onExpand(item: PrayerDetailModelsItem, position: Int) {

    }
}