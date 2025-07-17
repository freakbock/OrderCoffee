package com.example.ordercoffee.domain.usecase

import com.example.ordercoffee.domain.model.MenuItem
import com.example.ordercoffee.domain.repository.MenuRepository


class GetMenuUseCase(private val repository: MenuRepository) {
    suspend operator fun invoke(locationId: String): Result<List<MenuItem>> =
        repository.getMenu(locationId)
}

