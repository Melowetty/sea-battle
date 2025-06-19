package ru.sigma.game.domain.dto

import ru.sigma.data.domain.model.Event
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.domain.model.game.PlayerState
import java.util.UUID

data class ShotResultDto(
    val gameState: GameState,
    val afterShotState: AfterShotStateDto
)