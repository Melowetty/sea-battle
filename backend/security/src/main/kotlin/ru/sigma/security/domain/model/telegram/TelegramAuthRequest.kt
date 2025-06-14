package ru.sigma.security.domain.model.telegram

data class TelegramAuthRequest(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val photoUrl: String,
    val authDate: Long,
    val hash: String
)
