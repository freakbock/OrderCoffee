package com.example.ordercoffee.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordercoffee.domain.model.MenuItem
import com.example.ordercoffee.domain.usecase.GetMenuUseCase
import kotlinx.coroutines.launch

class MenuViewModel(
    private val getMenuUseCase: GetMenuUseCase
) : ViewModel() {

    private val _menuItems = MutableLiveData<List<MenuItem>>()
    val menuItems: LiveData<List<MenuItem>> = _menuItems

    fun loadMenu(locationId: String) {
        viewModelScope.launch {
            getMenuUseCase(locationId).onSuccess {
                _menuItems.value = it
            }
        }
    }
}

