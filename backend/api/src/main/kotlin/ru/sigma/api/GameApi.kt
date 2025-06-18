package ru.sigma.api

import ru.sigma.domain.dto.GameDto

interface GameApi {
    fun getGame(id: Long): GameDto

    fun leaveGame(id: Long)
}