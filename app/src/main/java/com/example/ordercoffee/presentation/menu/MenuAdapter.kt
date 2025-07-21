package com.example.ordercoffee.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ordercoffee.R
import com.example.ordercoffee.databinding.ItemMenuBinding
import com.example.ordercoffee.domain.model.MenuItem

class MenuAdapter(
    private val onCountChanged: (List<MenuItem>) -> Unit // вызывается при изменении количества
) : ListAdapter<MenuItem, MenuAdapter.MenuViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MenuViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuItem) {
            binding.item = item
            binding.itemImage.load(item.imageURL) {
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_foreground)
            }

            // Установка текущего значения count
            binding.count.text = item.count.toString()
            binding.itemName.text = item.name
            binding.itemPrice.text = item.price.toString()

            binding.tvPlus.setOnClickListener {
                item.count++
                binding.count.text = item.count.toString()
                onCountChanged(currentList)
            }

            binding.tvMinus.setOnClickListener {
                if (item.count > 0) {
                    item.count--
                    binding.count.text = item.count.toString()
                    onCountChanged(currentList)
                }
            }

            binding.executePendingBindings()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MenuItem>() {
            override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
