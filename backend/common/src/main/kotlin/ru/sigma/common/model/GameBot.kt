package ru.sigma.common.model

import java.util.UUID

enum class GameBot(
    val id: UUID
) {
    DOCTOR_LIVSI(UUID.fromString("00000000-0000-0000-0000-000000000001"))
}