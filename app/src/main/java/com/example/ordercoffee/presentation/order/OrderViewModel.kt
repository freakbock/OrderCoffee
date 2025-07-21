package com.example.ordercoffee.presentation.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ordercoffee.domain.model.MenuItem

class OrderViewModel : ViewModel() {
    private val _items = MutableLiveData<List<MenuItem>>()
    val items: LiveData<List<MenuItem>> = _items

    fun setItems(menuItems: List<MenuItem>) {
        _items.value = menuItems.filter { it.count > 0 }
    }
}
