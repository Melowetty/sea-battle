package ru.sigma.security.domain.model

data class AuthTokenRefreshRequest(
    val refreshToken: String
)
