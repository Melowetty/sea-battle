package ru.sigma.api

import ru.sigma.game.domain.dto.GameDto

interface GameApi {
    fun getGame(id: Long): GameDto

    fun leaveGame(id: Long)
}