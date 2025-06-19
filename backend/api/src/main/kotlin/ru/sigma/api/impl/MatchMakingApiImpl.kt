package ru.sigma.api.impl

import org.springframework.stereotype.Component
import ru.sigma.api.MatchMakingApi
import ru.sigma.common.dto.RoomDto
import ru.sigma.game.domain.dto.GameDto
import ru.sigma.matchmaking.service.MatchMakingService

@Component
class MatchMakingApiImpl(
    private val matchMakingService: MatchMakingService
): MatchMakingApi {
    override fun getRoom(code: String): RoomDto {
        return matchMakingService.getRoom(code)
    }

    override fun joinRandomRoom(): RoomDto {
        return matchMakingService.joinRandomRoom()
    }

    override fun joinRoom(code: String): RoomDto {
        return matchMakingService.joinRoom(code)
    }

    override fun createRoom(): RoomDto {
        return matchMakingService.createRoom()
    }

    override fun startRoom(code: String): GameDto {
        return matchMakingService.startGame(code)
    }

    override fun startRoomWithBots(code: String): GameDto {
        return matchMakingService.startGameWithBots(code)
    }

    override fun leaveRoom(code: String) {
        return matchMakingService.leaveRoom(code)
    }
}