package ru.sigma.api.impl

import org.springframework.stereotype.Component
import ru.sigma.api.MatchMakingApi

@Component
class MatchMakingApiImpl: MatchMakingApi {
    override fun joinRandomRoom() {
        throw NotImplementedError("Not implemented")
    }

    override fun joinRoom(code: String) {
        throw NotImplementedError("Not implemented")
    }

    override fun createRoom(request: Any) {
        throw NotImplementedError("Not implemented")
    }

    override fun startRoom(roomId: Long) {
        throw NotImplementedError("Not implemented")
    }

    override fun leaveRoom(roomId: Long) {
        throw NotImplementedError("Not implemented")
    }
}