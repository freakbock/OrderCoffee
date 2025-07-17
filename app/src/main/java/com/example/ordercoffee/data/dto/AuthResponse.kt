package com.example.ordercoffee.data.dto

data class AuthResponse(
    val token: String,
    val tokenLifeTime: Long
)
