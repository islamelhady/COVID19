package com.elhady.covid19.ui.faqs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elhady.covid19.data.model.FaqsData
import com.elhady.covid19.databinding.ItemFaqBinding

class FaqsAdapter :
    ListAdapter<FaqsData, FaqsAdapter.FaqsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<FaqsData>() {
        override fun areItemsTheSame(oldItem: FaqsData, newItem: FaqsData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FaqsData, newItem: FaqsData): Boolean {
            return newItem.question == oldItem.question
        }
    }

    class FaqsViewHolder(private val binding: ItemFaqBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(faq: FaqsData) {
            binding.faq = faq
        }

        companion object {
            fun from(parent: ViewGroup): FaqsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFaqBinding.inflate(layoutInflater, parent, false)
                return FaqsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqsViewHolder {
        return FaqsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FaqsViewHolder, position: Int) =
        holder.bind(getItem(position))

}