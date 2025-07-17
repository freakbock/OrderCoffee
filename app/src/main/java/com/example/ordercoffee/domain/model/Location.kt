package com.example.ordercoffee.domain.model

data class Location(
    val id: Int,
    val name: String,
    val point: Point
)

data class Point(
    val latitude: Double,
    val longitude: Double
)
