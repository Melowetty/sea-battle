package ru.sigma.game.domain.dto

import ru.sigma.common.model.Coordinate
import ru.sigma.data.domain.model.CellStatus

data class CoordinateStatusDto(
    val coordinate: Coordinate,
    val status: CellStatus
)
