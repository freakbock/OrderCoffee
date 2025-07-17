package com.example.ordercoffee.domain.repository

import com.example.ordercoffee.domain.model.AuthToken

interface AuthRepository {
    suspend fun login(login: String, password: String): Result<AuthToken>
    suspend fun register(login: String, password: String): Result<AuthToken>
}
