package com.example.ordercoffee.domain.model

data class AuthToken(
    val token: String,
    val tokenLifeTime: Long
)
