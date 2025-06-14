package ru.sigma.security.domain.model

data class AuthTokenResponse(
    val accessToken: String,
    val accessTokenExpiresIn: Long,
    val refreshToken: String,
    val refreshExpiresIn: Long
)
