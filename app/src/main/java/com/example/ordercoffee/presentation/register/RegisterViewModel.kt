package com.example.ordercoffee.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordercoffee.domain.model.AuthToken
import com.example.ordercoffee.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registerState = MutableLiveData<Result<AuthToken>>()
    val registerState: LiveData<Result<AuthToken>> = _registerState

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun register(email: String, password: String, confirmPassword: String) {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _errorMessage.value = "Заполните все поля"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorMessage.value = "Введите корректный email"
            return
        }

        if (password.length < 6) {
            _errorMessage.value = "Пароль должен быть не менее 6 символов"
            return
        }

        if (password != confirmPassword) {
            _errorMessage.value = "Пароли не совпадают"
            return
        }

        viewModelScope.launch {
            val result = registerUseCase(email, password)
            _registerState.value = result
        }
    }
}
