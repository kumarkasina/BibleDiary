package com.example.jesusapp.ui.BiblePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jesusapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_bible.*
import kotlinx.android.synthetic.main.bottomsheettext.*

class BottomSheetFragment(val texts : String) :  BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheettext, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_prayer.text= texts

    }

    companion object{
        fun newInstance(texts : String): BottomSheetFragment = BottomSheetFragment(texts)
    }
}