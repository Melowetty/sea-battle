package ru.sigma.security.api

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import ru.sigma.api.GameApi

@Component
class SecuredGameApi(
    @Qualifier("gameApiImpl")
    private val gameApi: GameApi
) : GameApi {
    override fun getGame(id: Long): Any {
        println("from secured")
        return gameApi.getGame(id)
    }

    override fun leaveGame(id: Long) {
        println("from secured")
        gameApi.leaveGame(id)
    }
}