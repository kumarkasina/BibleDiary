package com.example.jesusapp.ui.biblePage

import android.app.DatePickerDialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jesusapp.R
import com.example.jesusapp.data.model.DairyCategoriesModelItem
import com.example.jesusapp.data.remote.MainActivityViewState
import com.example.jesusapp.db.DiaryDao
import com.example.jesusapp.listener.OnItemClickListener

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_bible.*
import kotlinx.android.synthetic.main.activity_prayer_detail.horizontal_recycler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class BibleActivity : AppCompatActivity(), OnItemClickListener<DairyCategoriesModelItem>,
    DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var usersDao: DiaryDao

    lateinit var adapter: BiblePageAdapter
    val viewModel: BiblePageViewModel by viewModels()

    lateinit var date: String
    val formatter = SimpleDateFormat("dd-MM-yyyy")
    val api_formatter = SimpleDateFormat("yyyy-MM-dd")
    var calendar = Calendar.getInstance()
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var clickedString: String = ""

    private lateinit var mediaPlayer: MediaPlayer
    private var pause: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bible)

        txt_marq1.setSelected(true)
        mediaPlayer = MediaPlayer()

        initialUiState()
        observeViewState()
        observeUsersInDatabase()
        uparrow.setOnClickListener(View.OnClickListener {

            BottomSheetFragment.newInstance(
                clickedString
            ).show(supportFragmentManager, "Bootomsheet")
        })
        changeDate()
        //prepareMediaPlayer()

        img_left.setOnClickListener(View.OnClickListener {


            calendar.add(Calendar.DATE, -1)
            date = formatter.format(calendar.time)
            txt_date.text = date

            viewModel.getData(api_formatter.format(calendar.time))
            //adapter.setData()


        })
        img_right.setOnClickListener(View.OnClickListener {

            calendar.add(Calendar.DATE, 1)
            date = formatter.format(calendar.time)
            txt_date.text = date
            viewModel.getData(api_formatter.format(calendar.time))
            // adapter.setData()
        })
        img_date.setOnClickListener(View.OnClickListener {

            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(this, this, year, month, day)


            datePickerDialog.show()
        })
        img_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, prayer_text.text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        img_sound.setOnClickListener {
            prepareMediaPlayer()
            Toast.makeText(this, "media playing", Toast.LENGTH_SHORT).show()
        }

        mediaPlayer.setOnPreparedListener {
            Log.e("start", "music")
            it.start()
        }
    }

    private fun prepareMediaPlayer() {
        try {

            mediaPlayer.setDataSource(
                this,
                Uri.parse("http://vprbbc.streamguys.net:80/vprbbc24-mobile.mp3")
            )
            mediaPlayer.prepareAsync()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun changeDate() {
        calendar = Calendar.getInstance()
        date = formatter.format(calendar.time)
        txt_date.text = date

        //  viewModel.getData(api_formatter.format(calendar.time))
        try {
            Log.e("date", "" + api_formatter.format(calendar.time))
            viewModel.getData(api_formatter.format(calendar.time))
            //adapter.setData()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun observeViewState() {

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MainActivityViewState.ShowLoading -> {
                    Log.e("========", "==========")
                }
                is MainActivityViewState.ShowError -> {

                }
            }
        })


    }

    private fun initialUiState() {

        adapter = BiblePageAdapter(this, 1)
        horizontal_recycler.adapter = adapter
        horizontal_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observeUsersInDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            usersDao.getAllCategDistinctUntilChanged().collect { users ->
                showData(users)
                Log.e("count", "=" + users.size)
                adapter.setData()
            }
        }
    }

    private fun showData(data: List<DairyCategoriesModelItem>) {
        //Log.e("size", "${data.size}")
        horizontal_recycler.visibility = View.VISIBLE
        adapter.submitList(data)
    }

    private fun stoping() {
        if (mediaPlayer != null)
            if (mediaPlayer?.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.release()
            }

    }


    override fun onItemClick(item: DairyCategoriesModelItem, position: Int) {
        prayer_text.text = item?.message
        txt_heading.text = item?.heading
        clickedString = item?.message
        // adapter.notifyItemChanged(position)
        //  viewModel.getData(api_formatter.format(calendar.time))
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

        Log.e("Date", "" + p1 + "-" + p2 + "-" + p3)
        calendar.set(Calendar.YEAR, p1)
        calendar.set(Calendar.MONDAY, p2)
        calendar.set(Calendar.DAY_OF_MONTH, p3)
        txt_date.text = formatter.format(calendar.time)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer?.isPlaying)
            mediaPlayer.stop()
    }
}