package com.elhady.covid19.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elhady.covid19.R
import com.elhady.covid19.data.dto.Statewise
import com.elhady.covid19.databinding.ItemStateBinding
import java.text.SimpleDateFormat
import com.elhady.covid19.utils.getPeriod


/**
 * Created by islam elhady on 26-May-21.
 */
class StateAdapter : ListAdapter<Statewise, StateAdapter.StateViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Statewise>() {
        override fun areItemsTheSame(oldItem: Statewise, newItem: Statewise): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Statewise, newItem: Statewise): Boolean {
            return oldItem.state == newItem.state
        }
    }

    class StateViewHolder(val binding: ItemStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(statewise: Statewise) {
            binding.textState.text = statewise.state
            binding.textLastUpdatedView.text = itemView.context.getString(
                R.string.text_last_updated,
                getPeriod(
                    SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                        .parse(statewise.lastupdatedtime)
                )
            )

            binding.textConfirmed.text = statewise.confirmed
            binding.textActive.text = statewise.active
            binding.textRecovered.text = statewise.recovered
            binding.textDeath.text = statewise.deaths

            // New Confirmed
            statewise.deltaconfirmed.let {
                if (it == "0") {
                    binding.groupStateNewConfirm.visibility = View.GONE
                } else {
                    binding.groupStateNewConfirm.visibility = View.VISIBLE
                    binding.textStateNewConfirm.text = statewise.deltaconfirmed
                }
            }

            // New Recovered
            statewise.deltarecovered.let {
                if (it == "0") {
                    binding.groupStateNewRecover.visibility = View.GONE
                } else {
                    binding.groupStateNewRecover.visibility = View.VISIBLE
                    binding.textStateNewRecover.text = statewise.deltarecovered
                }
            }

            // New Deaths
            statewise.deltadeaths.let {
                if (it == "0") {
                    binding.groupStateNewDeaths.visibility = View.GONE
                } else {
                    binding.groupStateNewDeaths.visibility = View.VISIBLE
                    binding.textStateNewDeath.text = statewise.deltadeaths
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StateViewHolder(
        ItemStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) =
        holder.bind(getItem(position))


}