package ru.sigma.gamecore.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import ru.sigma.data.domain.entity.GameEntity
import ru.sigma.data.domain.model.game.PlayerState
import ru.sigma.data.domain.model.Event
import ru.sigma.data.domain.model.ShipStatus
import ru.sigma.data.domain.model.game.Coordinate
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.repository.GameRepository
import ru.sigma.data.repository.UserRepository
import java.sql.Time
import java.time.Instant
import java.util.UUID
import kotlin.collections.plus
@Service
class GameService(
    private val initService: InitService,
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository

) {

    fun startNewGame(
        gameId: Long,
        players: Map<UUID, List<List<List<Int>>>>,
        size: Int
    ) {
        var game = GameEntity(
            id = gameId,
            state = initService.initGameState(gameId, players, size),
            createdAt = Instant.now(),
            players = players.keys.mapNotNull { userRepository.findById(it).orElse(null) }.toMutableSet()
        )

        gameRepository.save(game)
    }

    fun checkShot(
        gameId: Long,
        shot: Coordinate
    ): Map<Event, PlayerState>? {
        val gameState = loadGameState(gameId)
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

        gameState.round++ // перееход на следующий раунд

        gameRepository.save(
            gameRepository.findById(gameId)
                .orElseThrow { EntityNotFoundException("Entity not found with id: $gameId") }
        )

        return mapOf(event to targetPlayerState)
    }

    private fun loadGameState(gameId: Long): GameState {
        val game = gameRepository.findById(gameId)
            .orElseThrow { EntityNotFoundException("Entity not found with id: $gameId") }

        return game.state ?: throw IllegalStateException("Game state is null")
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
                }
            }
        }

        return event
    }
}