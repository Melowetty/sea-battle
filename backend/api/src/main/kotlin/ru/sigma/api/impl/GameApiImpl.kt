package ru.sigma.api.impl

import org.springframework.stereotype.Component
import ru.sigma.api.GameApi
import ru.sigma.common.model.Coordinate
import ru.sigma.game.domain.dto.GameDto
import ru.sigma.game.domain.dto.ShotResultDto
import ru.sigma.game.service.GameService

@Component
class GameApiImpl(
    private val gameService: GameService,
): GameApi {
    override fun getGame(id: Long): GameDto {
        return gameService.getGameInfo(id)
    }

    override fun makeShot(id: Long, coords: Coordinate): ShotResultDto {
        return gameService.processTheShot(id, coords)
    }

    override fun leaveGame(id: Long) {
        throw NotImplementedError("Not implemented")
    }
}