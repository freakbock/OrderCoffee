package com.example.ordercoffee.data.repository

import com.example.ordercoffee.data.api.ApiService
import com.example.ordercoffee.data.dto.MenuItemDto
import com.example.ordercoffee.domain.model.MenuItem
import com.example.ordercoffee.domain.repository.MenuRepository
import com.example.ordercoffee.data.mapper.toDomain

class MenuRepositoryImpl(private val api: ApiService) : MenuRepository {
    override suspend fun getMenu(locationId: String): Result<List<MenuItem>> {
        return try {
            val response = api.getMenu(locationId)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body.map { it.toDomain() })
                } else Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
