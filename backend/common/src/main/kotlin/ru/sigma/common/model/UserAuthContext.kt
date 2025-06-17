package ru.sigma.common.model

import java.util.UUID

data class UserAuthContext(
    val id: UUID,
    val telegramId: Long,
)
