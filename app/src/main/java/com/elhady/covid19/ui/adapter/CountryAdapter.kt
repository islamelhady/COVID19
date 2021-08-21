package com.elhady.covid19.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elhady.covid19.data.model.CountriesItem
import com.elhady.covid19.databinding.ItemCountryBinding

class CountryAdapter() :
    ListAdapter<CountriesItem, CountryAdapter.StateViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CountriesItem>() {
        override fun areItemsTheSame(oldItem: CountriesItem, newItem: CountriesItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CountriesItem, newItem: CountriesItem): Boolean {
            return newItem.country == oldItem.country
        }
    }

    class StateViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            country: CountriesItem
//                 , onItemClickListener: OnItemClickListener? = null
        ) {
//            binding.textState.text = "${bindingAdapterPosition + 1}. ${country.country}"
//            binding.textLastUpdatedView.text = "| " + itemView.context.getString(
//                R.string.text_last_updated,
//                getPeriod(
//                    Date(country.updated)
//                )
//            )

            binding.textConfirmed.text = country.country
            binding.textActive.text = country.country
            binding.textRecovered.text = country.country
            binding.textDeath.text = country.country

//            binding.imageFlag.load(country.countryInfo?.flag) {
//                crossfade(true)
//                placeholder(R.color.windowBackground)
//                error(R.color.windowBackground)
//            }

//            onItemClickListener?.let { listener ->
//                binding.root.setOnClickListener {
//                    listener.onItemClicked(country)
//                }
//            }
        }

        companion object {
            fun from(parent: ViewGroup): StateViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCountryBinding.inflate(layoutInflater, parent, false)
                return StateViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        return StateViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) =
        holder.bind(
            getItem(position)
//            , onItemClickListener
        )


//    interface OnItemClickListener {
//        fun onItemClicked(country: Country)
//    }


}