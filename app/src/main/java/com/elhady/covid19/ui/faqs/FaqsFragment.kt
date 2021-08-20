package com.elhady.covid19.ui.faqs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elhady.covid19.R

class FaqsFragment : Fragment() {


    private lateinit var viewModel: FaqsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.faqs_fragment, container, false)
    }



}