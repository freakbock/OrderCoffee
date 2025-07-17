package com.example.ordercoffee.data.dto

import com.example.ordercoffee.domain.model.Location

data class LocationDto(
    val id: Int,
    val name: String,
    val point: PointDto
)

data class PointDto(
    val latitude: Double,
    val longitude: Double
)

