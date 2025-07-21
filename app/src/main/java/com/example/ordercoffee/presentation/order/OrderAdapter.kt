package com.example.ordercoffee.presentation.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ordercoffee.R
import com.example.ordercoffee.databinding.ItemMenuBinding
import com.example.ordercoffee.domain.model.MenuItem

class OrderAdapter : ListAdapter<MenuItem, OrderAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuItem) {

            binding.itemImage.load(item.imageURL) {
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_foreground)
            }

            binding.itemName.text = item.name
            binding.itemPrice.text = "₽ ${item.price}"
            // Показываем количество
            binding.count.text = item.count.toString()
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<MenuItem>() {
            override fun areItemsTheSame(old: MenuItem, new: MenuItem) = old.id == new.id
            override fun areContentsTheSame(old: MenuItem, new: MenuItem) = old == new
        }
    }
}

