package ru.sigma.game.domain.event

import java.util.UUID

data class GameFinishEvent(
    val gameId: Long,
    val winner: UUID
)
