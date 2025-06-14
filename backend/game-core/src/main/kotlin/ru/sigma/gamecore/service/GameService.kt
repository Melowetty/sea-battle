package ru.sigma.gamecore.service

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.EntityNotFoundException
import java.time.Instant
import java.util.UUID
import org.springframework.stereotype.Service
import ru.sigma.data.domain.entity.GameEntity
import ru.sigma.data.domain.model.Event
import ru.sigma.data.domain.model.ShipStatus
import ru.sigma.data.domain.model.game.Coordinate
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.domain.model.game.PlayerState
import ru.sigma.data.repository.GameRepository
import ru.sigma.data.repository.UserRepository

@Service
class GameService(
    private val initService: InitService,
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository,
    private val objectMapper: ObjectMapper,
) {

    fun startNewGame(
        gameId: Long,
        players: Map<UUID, List<List<List<Int>>>>,
        size: Int
    ) {
        val state = initService.initGameState(gameId, players, size)
        val stateAsString = objectMapper.writeValueAsString(state)

        val game = GameEntity(
            id = gameId,
            state = stateAsString,
            createdAt = Instant.now(),
            players = userRepository.findAllById(players.keys.toList()).toMutableSet()
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

        val gameState = objectMapper.readValue(game.state, GameState::class.java)

        return gameState
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