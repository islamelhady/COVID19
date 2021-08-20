package com.elhady.covid19.ui.global

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elhady.covid19.R
import com.elhady.covid19.databinding.FragmentGlobalBinding

class GlobalFragment : Fragment() {

    private lateinit var globalViewModel: GlobalViewModel
    private var _binding: FragmentGlobalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        globalViewModel =
            ViewModelProvider(this).get(GlobalViewModel::class.java)

        _binding = FragmentGlobalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        globalViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        setHasOptionsMenu(true)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_faqs -> {
//                val action = GlobalFragmentDirections
//                    .actionGlobalFragmentToFaqsFragment()
//                NavHostFragment.findNavController(this@GlobalFragment)
//                    .navigate(action)
                true
            }
            R.id.action_settings -> {
//                val action = GlobalFragmentDirections
//                    .actionGlobalFragmentToSettingsFragment()
//                NavHostFragment.findNavController(this@GlobalFragment)
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