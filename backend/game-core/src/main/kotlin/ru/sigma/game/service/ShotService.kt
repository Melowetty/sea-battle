package ru.sigma.game.service

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.EntityNotFoundException
import ru.sigma.common.model.Coordinate
import ru.sigma.data.domain.model.Event
import ru.sigma.data.domain.model.ShipStatus
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.domain.model.game.PlayerState
import ru.sigma.data.repository.GameRepository
import ru.sigma.data.repository.UserRepository
import ru.sigma.domain.dto.ShotResultDto
import java.util.UUID
import kotlin.collections.plus

class ShotService (
    private val gameRepository: GameRepository,
    private val objectMapper: ObjectMapper
) {

    fun checkShot(
        gameId: Long,
        gameState: GameState,
        shot: Coordinate
    ): ShotResultDto {
        val (currentPlayerId, targetPlayerId) = getCurrentAndTargetPlayerIds(gameState)

        val targetPlayerState = gameState.playersFields[targetPlayerId]
            ?: throw IllegalStateException("Target player state is null")

        val event = defineAnEvent(
            enemyPlayerState = targetPlayerState,
            shotCoordinate = shot
        )

        recordShotHistory(
            gameState = gameState,
            shooterId = currentPlayerId,
            shot = shot,
            event = event
        )

        gameRepository.save(
            gameRepository.findById(gameId)
                .orElseThrow { EntityNotFoundException("Entity not found with id: $gameId") }
        )

        // указываем следующего игрока
        var nextPlayer = currentPlayerId
        if (event == Event.MISS) { nextPlayer = targetPlayerId }

        return ShotResultDto(
            event = event,
            targetState = targetPlayerState,
            nextPlayer = nextPlayer
        )
    }



    private fun defineAnEvent(
        enemyPlayerState: PlayerState,
        shotCoordinate: Coordinate
    ): Event {
        var event = Event.MISS

        enemyPlayerState.ships.forEach { ship ->
            if (shotCoordinate in ship.coordinates) {
                enemyPlayerState.hits += shotCoordinate
                ship.healthPoints--
                event = Event.HIT

                if (ship.healthPoints == 0) {
                    ship.status = ShipStatus.DEAD
                    enemyPlayerState.aliveShips--
                    event = Event.DESTRUCTION

                    if (enemyPlayerState.aliveShips == 0) {
                        event = Event.ALL_DESTRUCTION
                    }
                }
            }
        }

        return event
    }



    private fun getCurrentAndTargetPlayerIds(gameState: GameState): Pair<UUID, UUID> {
        val round = gameState.round
        val currentPlayerId = gameState.players[1 - (round % 2)]
        val targetPlayerId = gameState.players[round % 2]
        return currentPlayerId to targetPlayerId
    }

    private fun recordShotHistory(
        gameState: GameState,
        shooterId: UUID,
        shot: Coordinate,
        event: Event
    ) {
        gameState.history += "$shooterId shot at $shot: $event"
    }
}