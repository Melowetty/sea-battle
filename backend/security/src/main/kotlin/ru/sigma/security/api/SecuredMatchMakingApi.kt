package ru.sigma.security.api

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import ru.sigma.api.MatchMakingApi

@Component
class SecuredMatchMakingApi(
    @Qualifier("matchMakingApiImpl")
    private val matchMakingApi: MatchMakingApi
) : MatchMakingApi {

    @PreAuthorize("isAuthenticated()")
    override fun joinRandomRoom() {
        println("from secured")
        return matchMakingApi.joinRandomRoom()
    }

    @PreAuthorize("isAuthenticated()")
    override fun joinRoom(code: String) {
        println("from secured")
        return matchMakingApi.joinRoom(code)
    }

    @PreAuthorize("isAuthenticated()")
    override fun createRoom(request: Any) {
        println("from secured")
        return matchMakingApi.createRoom(request)
    }

    @PreAuthorize("isAuthenticated()")
    override fun startRoom(roomId: Long) {
        println("from secured")
        return matchMakingApi.startRoom(roomId)
    }

    @PreAuthorize("isAuthenticated()")
    override fun leaveRoom(roomId: Long) {
        println("from secured")
        return matchMakingApi.leaveRoom(roomId)
    }
}