package com.example.ordercoffee.presentation.location_map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordercoffee.domain.model.Location
import com.example.ordercoffee.domain.usecase.GetLocationsUseCase
import kotlinx.coroutines.launch

class MapViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> = _locations

    fun loadLocations() {
        viewModelScope.launch {
            val result = getLocationsUseCase()
            if (result.isSuccess) {
                _locations.value = result.getOrNull()
            } else {
                // Обработка ошибки, например логирование или установка пустого списка
                _locations.value = emptyList()
            }
        }
    }

}
