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
import java.util.UUID
import kotlin.collections.plus


@Service
class GameService(
    private val initService: InitService,
    private val gameRepository: GameRepository
) {

    fun startNewGame(
        gameId: Long,
        players: Map<UUID,List<List<List<Int>>>>,
        size: Int
    ) {
        val playerStates = mutableMapOf<UUID, PlayerState>()
        val playerIds = players.keys.toList()

        players.forEach { (currentPlayerId, shipData) ->
            val enemyPlayerId = playerIds.first { it != currentPlayerId }
            val ships = shipData.map { initService.initShip(it) }

            playerStates[currentPlayerId] = initService.initPlayerState(
                currentPlayerId,
                ships
            )
        }

        var gameEntity: GameEntity = gameRepository.findById(gameId)
            .orElseThrow{EntityNotFoundException("Entity not found with id: $gameId")}
        gameEntity.state = initService.initGame(
            gameId,
            playerStates,
            size
        )

        gameRepository.save(gameEntity)
    }

    fun checkShot(
        gameId: Long,
        shot: List<Int>
    ): Map<Event, PlayerState>? {
        var gameEntity: GameEntity = gameRepository.findById(gameId)
            .orElseThrow{EntityNotFoundException("Entity not found with id: $gameId")}
        var gameState = gameEntity.state
            ?: throw IllegalStateException("Game state is null")
        var enemyPlayerState = gameState?.playersFields[gameState.targetPlayer]
            ?: throw IllegalStateException("Game state is null")
        val shotCoordinate = Coordinate(shot[0], shot[1])
        var event = defineAnEvent( // проверяем куда попал снаряд
            enemyPlayerState,
            shotCoordinate
        )

        swapPlayers(gameState) // меняем ход игроков
        gameRepository.save(gameEntity) // сохраняем в бд изменения

        return mapOf(event to enemyPlayerState)
    }

    private fun defineAnEvent(
        enemyPlayerState: PlayerState,
        shotCoordinate: Coordinate
    ): Event{
        var event = Event.MISS
        enemyPlayerState.ships.forEach { ship ->
            if (ship.coordinates.indexOf(shotCoordinate) != -1) {
                enemyPlayerState.hits += shotCoordinate
                ship.healthPoints--
                event = Event.HIT
                if (ship.healthPoints  == 0){
                    ship.status = ShipStatus.DEAD
                    enemyPlayerState.aliveShips--
                    event = Event.DESTRUCTION
                }
            }
        }
        return event
    }

    private fun swapPlayers(
        gameState: GameState
    ) {
        val currentActive = gameState.activePlayer
        gameState.activePlayer = gameState.targetPlayer
        gameState.targetPlayer = currentActive
    }
}
