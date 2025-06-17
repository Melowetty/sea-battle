package ru.sigma.common.dto

import java.util.UUID

data class UserDto(
    val id: UUID,
    val name: String,
    val avatar: String?,
    val isBot: Boolean
)
