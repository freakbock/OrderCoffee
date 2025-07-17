package com.example.ordercoffee.domain.usecase

import com.example.ordercoffee.domain.model.Location
import com.example.ordercoffee.domain.repository.LocationRepository

class GetLocationsUseCase(private val repository: LocationRepository) {
    suspend operator fun invoke(): Result<List<Location>> =
        repository.getLocations()
}
