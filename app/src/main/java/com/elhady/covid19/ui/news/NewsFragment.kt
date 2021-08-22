package com.elhady.covid19.ui.news

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.elhady.covid19.R
import com.elhady.covid19.databinding.FragmentNewsBinding
import com.elhady.covid19.ui.adapter.NewsAdapter
import com.elhady.covid19.utils.State
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModel()
    private var adapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater)

        setupAdapter()
        initData()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            toggleButton()
        }
    }

    private fun setupAdapter() {
        adapter = NewsAdapter()
        // Sets the adapter of the RecyclerView
        binding.recyclerNews.adapter = adapter
        postponeEnterTransition()
        binding.recyclerNews.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun initData() {
        viewModel.newsData.observe(viewLifecycleOwner, Observer { state ->
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
        viewModel.toggleButton.observe(viewLifecycleOwner, Observer { value ->
            if (value == true){
                binding.toggleButton.check(R.id.buttonWorld)
            }else binding.toggleButton.check(R.id.buttonWho)
        })

        toggleButton()
    }


    private fun toggleButton() {
        binding.toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.buttonWorld -> {
                        viewModel.getToggleButton(true)
                        viewModel.getNewsData()
                    }
                    R.id.buttonWho -> {
                        viewModel.getToggleButton(false)
                        Toast.makeText(requireContext(), "Whooo", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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