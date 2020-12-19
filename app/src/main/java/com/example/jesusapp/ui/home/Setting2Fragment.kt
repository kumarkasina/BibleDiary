package com.example.jesusapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jesusapp.R
import com.example.jesusapp.data.model.HomeDataModel1Item
import com.example.jesusapp.data.remote.MainActivityViewState
import com.example.jesusapp.db.UserDao
import com.example.jesusapp.listener.OnItemClickListener
import com.example.jesusapp.ui.DonorsList.DonorsActivity
import com.example.jesusapp.ui.QrCode.QRCodeActivity
import com.example.jesusapp.ui.latestnews.NewsActivity
import com.example.jesusapp.ui.programs.ProgramActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class Settings2Fragment : Fragment(), OnItemClickListener<HomeDataModel1Item> {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val viewModel: HomePageViewModel by viewModels()

    lateinit var adapter: HomePageAdapter

    @Inject
    lateinit var usersDao: UserDao
     lateinit var mcontext: Context



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mcontext=context

    }

    override fun onResume() {
        super.onResume()



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pageno = 2
        observeViewState()
        observeUsersInDatabase()

    }

    private fun  observeViewState()
    {
        activity?.let {
            viewModel.state.observe(it,  Observer { state ->
                when(state){
                    is MainActivityViewState.ShowLoading -> {
                        initialUiState()
                        showLoading()

                    }
                    is MainActivityViewState.ShowError -> {
                        showError(state.error)
                    }
                }
            })
        }

    }


    private fun observeUsersInDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            usersDao.get2AllFeaturesDistinctUntilChanged().collect { users ->
                showData(users)
                Log.e("count", "1")
            }
        }
    }

    private fun initialUiState(){
        progress_circular.visibility = View.GONE
        menu_list.visibility = View.GONE
        adapter = HomePageAdapter(this, 0)
        menu_list.adapter = adapter
        menu_list.layoutManager = GridLayoutManager(activity,2)
        menu_list.setHasFixedSize(true)
    }
    private fun showLoading(){
        progress_circular.visibility = View.VISIBLE
    }

    private fun showData(data: List<HomeDataModel1Item>) {
        removeProgressDialog()
        menu_list.visibility = View.VISIBLE

        adapter.submitList(data)
    }

    private fun showError(error: Throwable) {
        removeProgressDialog()
        Log.e("error",error.toString())
      //  showToast(error.message, Toast.LENGTH_LONG)
    }

    private fun removeProgressDialog() {
        progress_circular?.visibility = View.GONE
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStop() {
        super.onStop()


    }

    override fun onItemClick(item: HomeDataModel1Item, position: Int) {
        Log.e("clicked", "$position")
        if (position == 1) {
            val intent = Intent(activity, QRCodeActivity::class.java)
            startActivity(intent)
        } else if (position == 0) {

            val intent = Intent(activity, DonorsActivity::class.java)
            startActivity(intent)
        } else if (position == 2) {

            val intent = Intent(activity, NewsActivity::class.java)
            startActivity(intent)
        } else if (position == 3) {

            val intent = Intent(activity, ProgramActivity::class.java)
            startActivity(intent)
        } else if (position == 4) {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=claretian.biblediary"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}