package ru.sigma.security.api

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import ru.sigma.api.GameApi
import ru.sigma.common.model.Coordinate
import ru.sigma.game.domain.dto.AfterShotStateDto
import ru.sigma.game.domain.dto.GameDto

@Component
class SecuredGameApi(
    @Qualifier("gameApiImpl")
    private val gameApi: GameApi
) : GameApi {
    @PreAuthorize("isAuthenticated()")
    override fun getGame(id: Long): GameDto {
        return gameApi.getGame(id)
    }

    @PreAuthorize("isAuthenticated()")
    override fun makeShot(id: Long, coords: Coordinate): AfterShotStateDto {
        return gameApi.makeShot(id, coords)
    }

    @PreAuthorize("isAuthenticated()")
    override fun leaveGame(id: Long) {
        gameApi.leaveGame(id)
    }
}