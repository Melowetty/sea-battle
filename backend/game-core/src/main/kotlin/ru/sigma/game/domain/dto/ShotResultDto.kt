package ru.sigma.game.domain.dto

import ru.sigma.data.domain.model.Event
import ru.sigma.data.domain.model.game.PlayerState
import java.util.UUID

class ShotResultDto(
    val event: Event,
    val currentState: PlayerState,
    val nextPlayer: UUID
)