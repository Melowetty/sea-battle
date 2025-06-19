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
        return matchMakingApi.getRoom(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun joinRandomRoom(): RoomDto {
        return matchMakingApi.joinRandomRoom()
    }

    @PreAuthorize("isAuthenticated()")
    override fun joinRoom(code: String): RoomDto {
        return matchMakingApi.joinRoom(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun createRoom(): RoomDto {
        return matchMakingApi.createRoom()
    }

    @PreAuthorize("isAuthenticated()")
    override fun startRoom(code: String): GameDto {
        return matchMakingApi.startRoom(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun startRoomWithBots(code: String): GameDto {
        return matchMakingApi.startRoomWithBots(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun leaveRoom(code: String) {
        return matchMakingApi.leaveRoom(code)
    }
}