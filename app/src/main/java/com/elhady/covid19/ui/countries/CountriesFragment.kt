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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.MergeAdapter
import com.elhady.covid19.R
import com.elhady.covid19.data.model.Country
import com.elhady.covid19.databinding.FragmentCountriesBinding
import com.elhady.covid19.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment(), CountryAdapter.OnItemClickListener  {

    private lateinit var binding: FragmentCountriesBinding
    private val viewModel: CountriesViewModel by viewModel()
//    private val prefs: SharedPreferences by inject()
    private val countryAdapter = CountryAdapter(onItemClickListener = this)
    private val adapter = MergeAdapter(countryAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountriesBinding.inflate(inflater)
        setHasOptionsMenu(true)
        binding.recyclerCountries.adapter = adapter
        initData()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        viewModel.getAllCountriesData(getString(R.string.pref_sort))
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
                    val list = state.data
                    countryAdapter.submitList(list)
                    binding.swipeRefreshLayout.isRefreshing = false
                    scrollToTop()
                }
            }
        })
        viewModel.covidAllCountriesDataSearch.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> binding.swipeRefreshLayout.isRefreshing = false
                is State.Success -> {
                    val item = state.data
                    countryAdapter.submitList(listOf(item))
                    binding.swipeRefreshLayout.isRefreshing = false
                    scrollToTop()
                }
            }
        })

        loadData()
    }

    override fun onItemClicked(country: Country) {
//        tracker.track(CountryClickEvent(country.country.toString()))
        val action = CountriesFragmentDirections.actionNavigationCountriesToCountryDetailsFragment(country)
        NavHostFragment.findNavController(this@CountriesFragment)
            .navigate(action)
    }

    private fun scrollToTop() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(300)
            binding.recyclerCountries.scrollToPosition(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_countries, menu)

        var queryTextChangedJob: Job? = null
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getAllCountriesData(query, false)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (newText.length > 1) {
                        queryTextChangedJob?.cancel()

                        queryTextChangedJob = lifecycleScope.launch(Dispatchers.Main) {
                            delay(500)
                            viewModel.getAllCountriesData(newText, false)
                        }
                    }
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
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