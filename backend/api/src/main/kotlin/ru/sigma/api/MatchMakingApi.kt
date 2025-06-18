package ru.sigma.api

import ru.sigma.common.dto.RoomDto
import ru.sigma.domain.dto.GameDto

interface MatchMakingApi {
    fun getRoom(code: String): RoomDto

    fun joinRandomRoom(): RoomDto

    fun joinRoom(code: String): RoomDto

    fun createRoom(): RoomDto

    fun startRoom(code: String): GameDto

    fun startRoomWithBots(code: String): GameDto

    fun leaveRoom(code: String)
}