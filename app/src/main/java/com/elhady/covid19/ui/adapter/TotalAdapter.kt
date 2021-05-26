package com.elhady.covid19.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elhady.covid19.R
import com.elhady.covid19.data.dto.Statewise
import com.elhady.covid19.databinding.ItemTotalBinding
import com.elhady.covid19.utils.getPeriod
import java.text.SimpleDateFormat

/**
 * Created by islam elhady on 26-May-21.
 */
class TotalAdapter : ListAdapter<Statewise, TotalAdapter.TotalViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Statewise>() {
        override fun areItemsTheSame(oldItem: Statewise, newItem: Statewise): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Statewise, newItem: Statewise): Boolean {
            return oldItem.state == newItem.state
        }
    }

    class TotalViewHolder(private val binding: ItemTotalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(details: Statewise) {
            binding.textLastUpdatedView.text = itemView.context.getString(
                R.string.text_last_updated,
                getPeriod(
                    SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                        .parse(details.lastupdatedtime)
                )
            )

            binding.textConfirmed.text = details.confirmed
            binding.textActive.text = details.active
            binding.textRecovered.text = details.recovered
            binding.textDeceased.text = details.deaths

            // New Confirmed
            details.deltaconfirmed.let {
                if (it == "0") {
                    binding.groupNewConfirmed.visibility = View.GONE
                } else {
                    binding.groupNewConfirmed.visibility = View.VISIBLE
                    binding.textNewConfirmed.text = details.deltaconfirmed
                }
            }

            // New Recovered
            details.deltarecovered.let {
                if (it == "0") {
                    binding.groupNewRecovered.visibility = View.GONE
                } else {
                    binding.groupNewRecovered.visibility = View.VISIBLE
                    binding.textNewRecovered.text = details.deltarecovered
                }
            }

            // New Deaths
            details.deltadeaths.let {
                if (it == "0") {
                    binding.groupNewDeaths.visibility = View.GONE
                } else {
                    binding.groupNewDeaths.visibility = View.VISIBLE
                    binding.textNewDeaths.text = details.deltadeaths
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TotalViewHolder(
        ItemTotalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TotalViewHolder, position: Int) =
        holder.bind(getItem(position))

}