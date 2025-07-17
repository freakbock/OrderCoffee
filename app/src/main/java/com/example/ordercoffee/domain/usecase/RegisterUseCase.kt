package com.example.ordercoffee.domain.usecase

import com.example.ordercoffee.domain.model.AuthToken
import com.example.ordercoffee.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(login: String, password: String): Result<AuthToken> =
        authRepository.register(login, password)
}
