package com.elhady.covid19.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.MergeAdapter
import com.elhady.covid19.R
import com.elhady.covid19.databinding.MainFragmentBinding
import com.elhady.covid19.ui.adapter.StateAdapter
import com.elhady.covid19.ui.adapter.TotalAdapter
import com.elhady.covid19.utils.State
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by islam elhady on 26-May-21.
 */
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()
//    private var mTotalAdapter: TotalAdapter? = null
//    private var mStateAdapter: StateAdapter? = null
    private val mTotalAdapter = TotalAdapter()
    private val mStateAdapter = StateAdapter()
    private val adapter = MergeAdapter(mTotalAdapter, mStateAdapter)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)


        // Set adapter to the RecyclerView
        binding.recycler.adapter = adapter

        initData()
        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.swipeRefreshLayout.setOnRefreshListener {
            initData()
        }
    }



    private fun initData() {
        viewModel.covidLiveData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    val list = state.data.statewise
                    mTotalAdapter.submitList(list?.subList(0, 1))
                    mStateAdapter.submitList(list?.subList(1, list?.size - 1))
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        })
        loadData()
    }

    private fun loadData() {
        viewModel.getData()
    }

}