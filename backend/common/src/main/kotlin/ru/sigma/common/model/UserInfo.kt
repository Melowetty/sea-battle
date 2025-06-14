package ru.sigma.common.model

import java.util.UUID

data class UserInfo(
    val id: UUID,
    val telegramId: Long,
)
