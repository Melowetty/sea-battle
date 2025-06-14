package ru.sigma.api.impl

import org.springframework.stereotype.Component
import ru.sigma.api.GameApi

@Component
class GameApiImpl: GameApi {
    override fun getGame(id: Long): Any {
        throw NotImplementedError("Not implemented")
    }

    override fun leaveGame(id: Long) {
        throw NotImplementedError("Not implemented")
    }
}