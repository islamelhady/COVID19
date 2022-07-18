package com.elhady.covid19.ui.global

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elhady.covid19.R
import com.elhady.covid19.data.model.Global
import com.elhady.covid19.databinding.ItemTotalBinding
import com.elhady.covid19.utils.getFormattedNumber
import com.elhady.covid19.utils.getPeriod
import java.util.*

class TotalAdapter : ListAdapter<Global, TotalAdapter.TotalViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TotalViewHolder(
        ItemTotalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TotalViewHolder, position: Int) =
        holder.bind(getItem(position))


    class TotalViewHolder(private val binding: ItemTotalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(global: Global) {
            binding.textLastUpdatedView.text = itemView.context.getString(
                R.string.text_last_updated,
                getPeriod(
                    Date(global.updated)
                )
            )

            binding.textConfirmed.text = getFormattedNumber(global.cases)
            binding.textActive.text = getFormattedNumber(global.active)
            binding.textRecovered.text = getFormattedNumber(global.recovered)
            binding.textDeceased.text = getFormattedNumber(global.deaths)

            binding.textTodayCases.text =
                "${getFormattedNumber(global.todayCases.toDouble() / (global.cases.toDouble() - global.todayCases.toDouble()) * 100)}% ${getFormattedNumber(
                    global.todayCases
                )}"
            binding.textTodayDeaths.text =
                "${getFormattedNumber(global.todayDeaths.toDouble() / (global.deaths.toDouble() - global.todayDeaths.toDouble()) * 100)}% ${getFormattedNumber(
                    global.todayDeaths
                )}"

//            val adRequest = AdRequest.Builder().build()
//            binding.adView.loadAd(adRequest)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Global>() {
            override fun areItemsTheSame(oldItem: Global, newItem: Global): Boolean =
                oldItem.updated == newItem.updated

            override fun areContentsTheSame(oldItem: Global, newItem: Global): Boolean =
                oldItem == newItem

        }
    }
}