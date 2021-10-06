package com.elhady.covid19.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.elhady.covid19.R
import com.elhady.covid19.data.model.Country
import com.elhady.covid19.databinding.ItemCountryHeaderBinding

class CountryHeaderAdapter :
    ListAdapter<Country, CountryHeaderAdapter.CountryHeaderViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryHeaderViewHolder(
        ItemCountryHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CountryHeaderViewHolder, position: Int) =
        holder.bind(getItem(position))


    class CountryHeaderViewHolder(private val binding: ItemCountryHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.textState.text = country.country
            binding.imageFlag.load(country.countryInfo?.flag) {
                crossfade(true)
                placeholder(R.color.windowBackground)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem.updated == newItem.updated

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem == newItem

        }
    }
}