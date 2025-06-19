package ru.sigma.security.domain.model

data class AuthTokenRefreshResponse(
    val accessToken: String,
    val accessTokenExpiresIn: Long,
)
