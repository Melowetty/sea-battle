package ru.sigma.gamecore.domain.model

import java.util.UUID

data class BotTurnEvent (
    val gameId: Long,
    val botId: UUID
)
