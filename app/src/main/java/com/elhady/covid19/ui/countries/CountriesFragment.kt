package com.elhady.covid19.ui.countries

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.elhady.covid19.R
import com.elhady.covid19.databinding.FragmentCountriesBinding
import com.elhady.covid19.ui.adapter.CountryAdapter
import com.elhady.covid19.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment() {

    private lateinit var binding: FragmentCountriesBinding
    private val viewModel: CountriesViewModel by viewModel()
    private var adapter: CountryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountriesBinding.inflate(inflater)
        setHasOptionsMenu(true)
        setupAdapter()
        initData()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    private fun setupAdapter() {
        adapter = CountryAdapter()
        // Sets the adapter of the RecyclerView
        binding.recyclerCountries.adapter = adapter
        postponeEnterTransition()
        binding.recyclerCountries.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun loadData() {
        viewModel.getAllCountriesData()
    }

    private fun initData() {
        viewModel.covidAllCountriesData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    if (state.data.isNotEmpty())
                        adapter?.submitList(state.data)
                    else
                        Toast.makeText(activity, "No Data", Toast.LENGTH_SHORT).show()
                    binding.swipeRefreshLayout.isRefreshing = false
//                    val list = state.data.country
//                    adapter?.submitList(list)
//                    binding.swipeRefreshLayout.isRefreshing = false
                    scrollToTop()
                }
            }
        })
        loadData()
    }

    private fun scrollToTop() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(300)
            binding.recyclerCountries.scrollToPosition(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_countries, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.queryHint = getString(R.string.search_hint)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                when (item.itemId) {
                    R.id.action_search -> {
                        true
                    }
                    R.id.action_sort -> {
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
    }

}