package com.example.ordercoffee.presentation.location_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordercoffee.domain.model.Location
import com.example.ordercoffee.domain.usecase.GetLocationsUseCase
import kotlinx.coroutines.launch

class LocationListViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> = _locations

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        fetchLocations()
    }

    private fun fetchLocations() {
        viewModelScope.launch {
            val result = getLocationsUseCase()
            result.onSuccess {
                _locations.value = it
                _error.value = null
            }
            result.onFailure {
                _error.value = it.message
            }
        }
    }
}
