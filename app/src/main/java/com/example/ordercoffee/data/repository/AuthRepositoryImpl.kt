package com.example.ordercoffee.data.repository

import com.example.ordercoffee.data.TokenStorage
import com.example.ordercoffee.data.api.ApiService
import com.example.ordercoffee.data.dto.AuthRequest
import com.example.ordercoffee.data.dto.AuthResponse
import com.example.ordercoffee.domain.model.AuthToken
import com.example.ordercoffee.domain.repository.AuthRepository
import com.example.ordercoffee.data.mapper.toDomain

class AuthRepositoryImpl(private val api: ApiService, private val tokenStorage: TokenStorage) : AuthRepository {
    override suspend fun login(login: String, password: String): Result<AuthToken> =
        safeApiCall {
            api.login(AuthRequest(login, password))
        }

    override suspend fun register(login: String, password: String): Result<AuthToken> =
        safeApiCall {
            api.register(AuthRequest(login, password))
        }

    private suspend fun safeApiCall(call: suspend () -> retrofit2.Response<AuthResponse>): Result<AuthToken> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {

                    tokenStorage.saveToken(body.token)
                    Result.success(body.toDomain())
                }
                else Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
