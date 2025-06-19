package ru.sigma.api

import ru.sigma.common.model.Coordinate
import ru.sigma.game.domain.dto.AfterShotStateDto
import ru.sigma.game.domain.dto.GameDto

interface GameApi {
    fun getGame(id: Long): GameDto
    fun makeShot(id: Long, coords: Coordinate): AfterShotStateDto
    fun leaveGame(id: Long)
}