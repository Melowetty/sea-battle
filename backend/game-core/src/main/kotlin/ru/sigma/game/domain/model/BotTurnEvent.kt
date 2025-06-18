package ru.sigma.game.domain.model

import ru.sigma.data.domain.model.game.PlayerState
import java.util.UUID

data class BotTurnEvent (
    val targetState: PlayerState,
    val size: Int,
    val botId: UUID
)
