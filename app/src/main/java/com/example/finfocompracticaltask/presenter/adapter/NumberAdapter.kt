package com.example.finfocompracticaltask.presenter.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finfocompracticaltask.R
import com.example.finfocompracticaltask.data.model.AppRules
import com.example.finfocompracticaltask.data.model.NumberItem
import com.example.finfocompracticaltask.databinding.ItemNumbersBinding
import com.example.finfocompracticaltask.utils.getColorCompat

class NumberAdapter : ListAdapter<NumberItem, NumberAdapter.NumberViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding = ItemNumbersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val numberItem = getItem(position)
        holder.bind(numberItem)
    }

    inner class NumberViewHolder(private val binding: ItemNumbersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(numberItem: NumberItem) {
            binding.apply {
                tvNumber.text = numberItem.number.toString()
                tvNumber.setTextColor(if (numberItem.isSelected) Color.WHITE else Color.BLACK)

                tvNumber.context.apply {
                    val color = if (numberItem.isSelected) {
                        when (numberItem.appRules) {
                            AppRules.ODD_NUMBER -> getColorCompat(R.color.color_odd_numbers)
                            AppRules.EVEN_NUMBER -> getColorCompat(R.color.color_even_numbers)
                            AppRules.PRIME_NUMBER -> getColorCompat(R.color.color_prime_numbers)
                            AppRules.FIBONACCI_SEQUENCE -> getColorCompat(R.color.color_fibonacci_numbers)
                        }
                    } else {
                        Color.LTGRAY
                    }
                    root.setBackgroundColor(color)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NumberItem>() {
        override fun areItemsTheSame(oldItem: NumberItem, newItem: NumberItem): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: NumberItem, newItem: NumberItem): Boolean {
            return oldItem == newItem
        }
    }

}