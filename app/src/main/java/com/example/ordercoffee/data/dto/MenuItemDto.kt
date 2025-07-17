package com.example.ordercoffee.data.dto

import com.example.ordercoffee.domain.model.MenuItem

data class MenuItemDto(
    val id: Int,
    val name: String,
    val imageURL: String,
    val price: Double
)



