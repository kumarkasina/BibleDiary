package com.example.jesusapp.ui.BiblePage

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
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.data.remote.MainActivityViewState
import com.example.jesusapp.db.UserDao
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.HomeDetail.HomeAdapter2
import com.example.jesusapp.ui.home.SharedHomeViewModel
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
class BibleActivity : AppCompatActivity(), OnItemClickListener<Users>,DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var usersDao: UserDao
    lateinit var adapter : HomeAdapter2
    val viewModel: SharedHomeViewModel by viewModels()

    lateinit var date : String
    val formatter = SimpleDateFormat("dd-MM-yyyy")
     var calendar = Calendar.getInstance()
    var day = 0
    var month: Int = 0
    var year: Int = 0

    private lateinit var mediaPlayer: MediaPlayer
    private var pause:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bible)
        mediaPlayer= MediaPlayer()
        observeViewState()
        observeUsersInDatabase()
        uparrow.setOnClickListener(View.OnClickListener {

        BottomSheetFragment.newInstance("బిగ్Cబాస్ : అఖిల్పై రాహుల్ షాకింగ్ కామెంట్స్ నటుల మధ్య" +
                    " చిచ్చుపెట్టిన గ్రేటర్ పోరు చిచ్చుపెట్టిన గ్రేటర్ పోరు చిచ్చుపెట్టిన గ్రేటర్ పోరు చిచ్చుపెట్టిన గ్రేటర్ పోరు చిచ్చుపెట్టిన గ్రేటర్ పోరు చిచ్చుపెట్టిన గ్రేటర్ పోరు చిచ్చుపెట్టిన గ్రేటర్ పోరు చిచ్చుపెట్టిన గ్రేటర్ పోరు").show(supportFragmentManager, "Bootomsheet")
        })


        changeDate()
        //prepareMediaPlayer()

        img_left.setOnClickListener(View.OnClickListener {



            calendar.add(Calendar.DATE, -1)
            date = formatter.format(calendar.time)
            txt_date.text =date

        })
        img_right.setOnClickListener(View.OnClickListener {

            calendar.add(Calendar.DATE, 1)
            date = formatter.format(calendar.time)
            txt_date.text =date
        })
        img_date.setOnClickListener(View.OnClickListener {

            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(this, this, year, month,day)


            datePickerDialog.show()
        })
        img_share.setOnClickListener{
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

        }catch (e:Exception){
                e.printStackTrace()
        }
    }

    private fun changeDate() {
        calendar = Calendar.getInstance()
         date = formatter.format(calendar.time)
        txt_date.text =date

    }

    private fun  observeViewState()
    {

        viewModel.state.observe(this,  Observer { state ->
            when(state){
                is MainActivityViewState.ShowLoading -> {
                    initialUiState()

                }
                is MainActivityViewState.ShowError -> {

                }
            }
        })


    }

    private fun initialUiState() {

        adapter = HomeAdapter2(this,1)
        horizontal_recycler.adapter = adapter
        horizontal_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)


    }
    fun observeUsersInDatabase(){
        CoroutineScope(Dispatchers.Main).launch {
            usersDao.getAllUsersDistinctUntilChanged().collect { users ->
                showData(users)
                Log.e("count", "1")
            }
        }
    }

    private fun showData(data: List<Users>) {
        Log.e("size", "${data.size}")
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


    override fun onItemClick(item: Users, position: Int) {
        prayer_text.text = item?.first_name
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