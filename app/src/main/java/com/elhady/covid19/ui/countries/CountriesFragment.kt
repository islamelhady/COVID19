package com.elhady.covid19.ui.countries

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elhady.covid19.R
import com.elhady.covid19.databinding.FragmentCountriesBinding

class CountriesFragment : Fragment() {

    private lateinit var countriesViewModel: CountriesViewModel
    private var _binding: FragmentCountriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        countriesViewModel =
            ViewModelProvider(this).get(CountriesViewModel::class.java)

        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        countriesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        setHasOptionsMenu(true)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_countries, menu)

//        var queryTextChangedJob: Job? = null
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.queryHint = getString(R.string.search_hint)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
////                query?.let {
////                    viewModel.getAllCountriesData(query, false)
////                }
//                return true
//            }

//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    if (newText.length > 1) {
//                        queryTextChangedJob?.cancel()
//
//                        queryTextChangedJob = lifecycleScope.launch(Dispatchers.Main) {
//                            delay(500)
//                            viewModel.getAllCountriesData(newText, false)
//                        }
//                    }
//                }
//                return false
//            }
//        })
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
//                tracker.track(SearchClickEvent("Country"))
                true
            }
            R.id.action_sort -> {
//                displayDialog()
                true
            }
            R.id.action_faqs -> {
//                val action = CountryFragmentDirections
//                    .actionCountryFragmentToFaqsFragment()
//                NavHostFragment.findNavController(this@CountryFragment)
//                    .navigate(action)
                true
            }
            R.id.action_settings -> {
//                val action = CountryFragmentDirections
//                    .actionCountryFragmentToSettingsFragment()
//                NavHostFragment.findNavController(this@CountryFragment)
//                    .navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}