package ru.sigma.security.api

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import ru.sigma.api.GameApi
import ru.sigma.domain.dto.GameDto

@Component
class SecuredGameApi(
    @Qualifier("gameApiImpl")
    private val gameApi: GameApi
) : GameApi {
    @PreAuthorize("isAuthenticated()")
    override fun getGame(id: Long): GameDto {
        println("from secured")
        return gameApi.getGame(id)
    }

    @PreAuthorize("isAuthenticated()")
    override fun leaveGame(id: Long) {
        println("from secured")
        gameApi.leaveGame(id)
    }
}