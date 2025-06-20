package ru.sigma.game.domain.event

import ru.sigma.game.domain.dto.AfterShotStateDto

data class GameTurnEvent(
    val gameId: Long,
    val event: AfterShotStateDto
)
