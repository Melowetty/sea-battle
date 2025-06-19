package ru.sigma.game.domain.dto

import ru.sigma.data.domain.model.Event
import java.util.UUID

data class AfterShotStateDto(
    val event: Event,
    val targetPlayer: UUID,
    val nextPlayer: UUID,
    val fieldsDifference: List<CoordinateStatusDto>
)
