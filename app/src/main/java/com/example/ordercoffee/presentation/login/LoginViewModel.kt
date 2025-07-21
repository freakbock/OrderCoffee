package com.example.ordercoffee.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.ordercoffee.domain.model.AuthToken
import com.example.ordercoffee.domain.usecase.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<Result<AuthToken>>()
    val loginState: LiveData<Result<AuthToken>> = _loginState

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun login(email: String, password: String) {
        when {
            email.isBlank() || password.isBlank() -> {
                _errorMessage.value = "Заполните все поля"
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _errorMessage.value = "Введите корректный email"
            }
            password.length < 6 -> {
                _errorMessage.value = "Пароль должен быть не менее 6 символов"
            }
            else -> {
                _errorMessage.value = null
                viewModelScope.launch {
                    val result = loginUseCase(email, password)
                    _loginState.value = result
                }
            }
        }
    }


}
