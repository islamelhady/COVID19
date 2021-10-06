package com.elhady.covid19.ui.countries

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elhady.covid19.R
import com.elhady.covid19.data.model.Country
import com.elhady.covid19.databinding.ItemTotalCountryBinding
import com.elhady.covid19.utils.getFormattedNumber
import com.elhady.covid19.utils.getPeriod
import java.util.*

class CountryTotalAdapter :
    ListAdapter<Country, CountryTotalAdapter.TotalViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TotalViewHolder(
        ItemTotalCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TotalViewHolder, position: Int) =
        holder.bind(getItem(position))


    class TotalViewHolder(private val binding: ItemTotalCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(country: Country) {
            binding.textLastUpdatedView.text = itemView.context.getString(
                R.string.text_last_updated,
                getPeriod(
                    Date(country.updated)
                )
            )

            binding.textConfirmed.text = getFormattedNumber(country.cases)
            binding.textActive.text = getFormattedNumber(country.active)
            binding.textRecovered.text = getFormattedNumber(country.recovered)
            binding.textDeceased.text = getFormattedNumber(country.deaths)


            binding.textTodayCases.text =
                "${getFormattedNumber(country.todayCases.toDouble() / (country.cases.toDouble() - country.todayCases.toDouble()) * 100)}% ${getFormattedNumber(
                    country.todayCases
                )}"
            binding.textTodayDeaths.text =
                "${getFormattedNumber(country.todayDeaths.toDouble() / (country.deaths.toDouble() - country.todayDeaths.toDouble()) * 100)}% ${getFormattedNumber(
                    country.todayDeaths
                )}"

//            val adRequest = AdRequest.Builder().build()
//            binding.adView.loadAd(adRequest)
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