package com.example.diarysnap.data.repository

interface AuthRepository {
    fun isLoggedIn(): Boolean
    fun currentUserEmail(): String?
    fun login(email: String, password: String): Result<Unit>
    fun logout()
}

