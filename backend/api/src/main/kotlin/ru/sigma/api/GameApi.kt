package ru.sigma.api

import ru.sigma.common.model.Coordinate
import ru.sigma.game.domain.dto.GameDto
import ru.sigma.game.domain.dto.ShotResultDto

interface GameApi {
    fun getGame(id: Long): GameDto
    fun makeShot(id: Long, coords: Coordinate): ShotResultDto
    fun leaveGame(id: Long)
}