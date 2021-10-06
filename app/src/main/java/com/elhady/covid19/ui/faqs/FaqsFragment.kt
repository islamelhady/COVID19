package com.elhady.covid19.ui.faqs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.elhady.covid19.databinding.FaqsFragmentBinding
import com.elhady.covid19.utils.State
import org.koin.android.viewmodel.ext.android.viewModel

class FaqsFragment : Fragment() {

    private lateinit var binding: FaqsFragmentBinding
    private val viewModel: FaqsViewModel by viewModel()
    private var adapter: FaqsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FaqsFragmentBinding.inflate(inflater)

        setupAdapter()
        initData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener { loadData() }

    }

    private fun setupAdapter() {
        adapter = FaqsAdapter()
        // Sets the adapter of the RecyclerView
        binding.recyclerFaqs.adapter = adapter
        postponeEnterTransition()
        binding.recyclerFaqs.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun initData() {
        viewModel.faqsData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    val list = state.data
                    adapter?.submitList(list.data)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        })

        loadData()
    }

    private fun loadData() {
        viewModel.getFaqsData()
    }



}