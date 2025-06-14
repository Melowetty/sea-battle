package ru.sigma.security.domain.model

data class TokenInfo(
    val token: String,
    val expiresIn: Int,
)
