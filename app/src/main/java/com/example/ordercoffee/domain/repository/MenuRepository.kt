package com.example.ordercoffee.domain.repository

import com.example.ordercoffee.domain.model.MenuItem

interface MenuRepository {
    suspend fun getMenu(locationId: String): Result<List<MenuItem>>
}
