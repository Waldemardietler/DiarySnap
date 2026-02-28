package com.example.diarysnap.data.repository

//  "Fake Auth" (lokal, simpel)
class AuthRepositoryImpl : AuthRepository {

    private var loggedInEmail: String? = null

    override fun isLoggedIn(): Boolean = loggedInEmail != null

    override fun currentUserEmail(): String? = loggedInEmail

    override fun login(email: String, password: String): Result<Unit> {
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Email/Passwort darf nicht leer sein"))
        }
        loggedInEmail = email.trim()
        return Result.success(Unit)
    }

    override fun logout() {
        loggedInEmail = null
    }
}

