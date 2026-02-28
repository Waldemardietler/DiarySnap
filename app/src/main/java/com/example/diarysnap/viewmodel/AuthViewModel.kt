package com.example.diarysnap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.diarysnap.AppServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth = AppServices.authRepository

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(email: String, password: String): Boolean {
        _error.value = null
        val result = auth.login(email, password)
        return if (result.isSuccess) true else {
            _error.value = result.exceptionOrNull()?.message ?: "Login fehlgeschlagen"
            false
        }
    }
}

