package com.elhady.covid19.ui.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.MergeAdapter
import com.elhady.covid19.databinding.FragmentCountryDetailsBinding
import com.elhady.covid19.ui.MainActivity
import com.elhady.covid19.utils.State


class CountryDetailsFragment : Fragment() {

    private val args: CountryDetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentCountryDetailsBinding
    private val viewModel: CountriesViewModel by viewModels()
    private val mTotalAdapter = CountryTotalAdapter()
    private val mChartAdapter = CountryChartAdapter()
    private val mCountryHeaderAdapter = CountryHeaderAdapter()
    private val adapter = MergeAdapter(mCountryHeaderAdapter, mTotalAdapter, mChartAdapter)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountryDetailsBinding.inflate(inflater)

        setHasOptionsMenu(true)
        binding.recycler.adapter = adapter

        (activity as MainActivity).supportActionBar?.title = args.country.country
        initData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
        return binding.root
    }

    private fun initData() {
        mCountryHeaderAdapter.submitList(listOf(args.country))
        mTotalAdapter.submitList(listOf(args.country))
        viewModel.covidCountryHistoricalData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    val item = state.data
                    mChartAdapter.submitList(listOf(item))
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        })

        loadData()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavHostFragment.findNavController(this)
                .navigateUp()
        }
        return true
    }

    private fun loadData() {
        viewModel.getCountryHistoricalData(args.country.country.toString())
    }
}