package com.example.ordercoffee.domain.repository

import com.example.ordercoffee.domain.model.Location

interface LocationRepository {
    suspend fun getLocations(): Result<List<Location>>
}
