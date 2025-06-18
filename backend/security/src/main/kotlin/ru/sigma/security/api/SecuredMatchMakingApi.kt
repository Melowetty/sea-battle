package ru.sigma.security.api

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import ru.sigma.api.MatchMakingApi
import ru.sigma.common.dto.RoomDto
import ru.sigma.game.domain.dto.GameDto

@Component
class SecuredMatchMakingApi(
    @Qualifier("matchMakingApiImpl")
    private val matchMakingApi: MatchMakingApi
) : MatchMakingApi {
    override fun getRoom(code: String): RoomDto {
        println("from secured")
        return matchMakingApi.getRoom(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun joinRandomRoom(): RoomDto {
        println("from secured")
        return matchMakingApi.joinRandomRoom()
    }

    @PreAuthorize("isAuthenticated()")
    override fun joinRoom(code: String): RoomDto {
        println("from secured")
        return matchMakingApi.joinRoom(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun createRoom(): RoomDto {
        println("from secured")
        return matchMakingApi.createRoom()
    }

    @PreAuthorize("isAuthenticated()")
    override fun startRoom(code: String): GameDto {
        println("from secured")
        return matchMakingApi.startRoom(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun startRoomWithBots(code: String): GameDto {
        println("from secured")
        return matchMakingApi.startRoomWithBots(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun leaveRoom(code: String) {
        println("from secured")
        return matchMakingApi.leaveRoom(code)
    }
}