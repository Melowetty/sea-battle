package ru.sigma.security.api

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import ru.sigma.api.MatchMakingApi

@Component
class SecuredMatchMakingApi(
    @Qualifier("matchMakingApiImpl")
    private val matchMakingApi: MatchMakingApi
) : MatchMakingApi {
    override fun joinRandomRoom() {
        println("from secured")
        return matchMakingApi.joinRandomRoom()
    }

    override fun joinRoom(code: String) {
        println("from secured")
        return matchMakingApi.joinRoom(code)
    }

    override fun createRoom(request: Any) {
        println("from secured")
        return matchMakingApi.createRoom(request)
    }

    override fun startRoom(roomId: Long) {
        println("from secured")
        return matchMakingApi.startRoom(roomId)
    }

    override fun leaveRoom(roomId: Long) {
        println("from secured")
        return matchMakingApi.leaveRoom(roomId)
    }
}