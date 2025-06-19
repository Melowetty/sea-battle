package ru.sigma.game.domain.dto

import ru.sigma.data.domain.model.Event

data class EventChangesDto(
    val event: Event,
    val fieldsDifference: List<CoordinateStatusDto>
)
