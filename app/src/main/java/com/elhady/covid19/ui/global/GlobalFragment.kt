package com.elhady.covid19.ui.global

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.MergeAdapter
import com.elhady.covid19.R
import com.elhady.covid19.databinding.FragmentGlobalBinding
import com.elhady.covid19.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class GlobalFragment : Fragment() {

    private lateinit var binding: FragmentGlobalBinding
    private val viewModel: GlobalViewModel by viewModel()

    private val mTotalAdapter = TotalAdapter()
    private val mChartAdapter = ChartAdapter()
    private val adapter = MergeAdapter(mTotalAdapter, mChartAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGlobalBinding.inflate(inflater)

        setHasOptionsMenu(true)

        binding.recycler.adapter = adapter

        initData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }

        return binding.root
    }

    private fun initData() {
        viewModel.covidGlobalData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    val item = state.data
                    mTotalAdapter.submitList(listOf(item))
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        })

        viewModel.covidAllHistoricalData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    val item = state.data
                    lifecycleScope.launch(Dispatchers.Main) {
                        delay(500)
                        mChartAdapter.submitList(listOf(item))
                        binding.swipeRefreshLayout.isRefreshing = false
                        binding.recycler.scrollToPosition(0)
                    }
                }
            }
        })

        loadData()
    }

    private fun loadData() {
        viewModel.getGlobalData()
        viewModel.getAllHistoricalData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}