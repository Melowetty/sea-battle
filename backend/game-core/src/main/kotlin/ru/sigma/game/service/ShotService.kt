package ru.sigma.game.service

import jakarta.persistence.EntityNotFoundException
import java.util.UUID
import org.springframework.stereotype.Service
import ru.sigma.common.model.Coordinate
import ru.sigma.data.domain.model.Event
import ru.sigma.data.domain.model.ShipStatus
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.domain.model.game.PlayerState
import ru.sigma.data.domain.model.game.Ship
import ru.sigma.data.repository.GameRepository
import ru.sigma.game.domain.dto.ShotResultDto

@Service
class ShotService (
    private val gameRepository: GameRepository,
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
            shotCoordinate = shot,
            gameState.fieldSize
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
        if (event == Event.MISS) {
            nextPlayer = targetPlayerId
        }

        return ShotResultDto(
            event = event,
            targetState = targetPlayerState,
            nextPlayer = nextPlayer
        )
    }

    private fun defineAnEvent(
        enemyPlayerState: PlayerState,
        shotCoordinate: Coordinate,
        fieldSize: Int
    ): Event {
        var event = Event.MISS

        enemyPlayerState.ships.forEach { ship ->
            if (shotCoordinate in ship.coordinates) {
                enemyPlayerState.hits += shotCoordinate // добавляем попадание
                ship.healthPoints--
                event = Event.HIT

                if (ship.healthPoints == 0) {
                    ship.status = ShipStatus.DEAD
                    enemyPlayerState.aliveShips--
                    event = Event.DESTRUCTION
                    // перемещаем попадания в разрушения с удалением
//                        var hitsList: MutableList<Coordinate> = enemyPlayerState.hits as MutableList<Coordinate>
//                        ship.coordinates.forEach { shipCoordinate ->
//                            hitsList.remove(shipCoordinate)
//                            enemyPlayerState.destructions += shipCoordinate
//                        }
//                        enemyPlayerState.hits = hitsList as List<Coordinate>
                    enemyPlayerState.hits = enemyPlayerState.hits - ship.coordinates.toSet() // непроверено что работает
                    enemyPlayerState.destructions += ship.coordinates
                    makeMissAroundDestruction(enemyPlayerState, fieldSize, ship)
                    if (enemyPlayerState.aliveShips == 0) {
                        event = Event.ALL_DESTRUCTION
                    }
                }
            }
        }
        return event
    }

    private fun makeMissAroundDestruction(
        state: PlayerState,
        fieldSize: Int,
        currentShip: Ship
    ) {
        currentShip.coordinates.forEach { currentDestructionCoordinate ->
            for (direction in listOf(1 to 0, 1 to 1, 1 to -1, -1 to 0, -1 to -1, -1 to 1, 0 to 1, 0 to -1)) {
                val targetCoordinate = Coordinate(currentDestructionCoordinate.x + direction.first, currentDestructionCoordinate.y + direction.second)
                when {
                    !targetCoordinate.isValid(fieldSize) -> continue
                    targetCoordinate in currentShip.coordinates -> continue
                    targetCoordinate in state.misses -> continue
                    else -> state.misses += targetCoordinate
                }
            }
        }

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

    private fun Coordinate.isValid(fieldSize: Int) =
        x in 0 until fieldSize && y in 0 until fieldSize
}