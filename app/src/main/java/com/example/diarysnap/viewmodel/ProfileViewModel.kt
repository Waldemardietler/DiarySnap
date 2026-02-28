package com.example.diarysnap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.diarysnap.AppServices

class ProfileViewModel : ViewModel() {
    fun email(): String = AppServices.authRepository.currentUserEmail() ?: "-"
    fun logout() = AppServices.authRepository.logout()
}

