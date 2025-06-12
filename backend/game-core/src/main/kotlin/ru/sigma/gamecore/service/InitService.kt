package ru.sigma.gamecore.service

import ru.sigma.data.domain.model.ShipStatus
import ru.sigma.data.domain.model.game.Coordinate
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.domain.model.game.PlayerState
import ru.sigma.data.domain.model.game.Ship
import java.util.UUID

class InitService {

    fun initGameState(
        gameId: Long,
        players: Map<UUID, List<List<List<Int>>>>,
        size: Int
    ): GameState {
        val playerStates = mutableMapOf<UUID, PlayerState>()
        val playerIds = players.keys.toList()

        players.forEach { (currentPlayerId, shipData) ->
            val ships = shipData.map { initShip(it) }

            playerStates[currentPlayerId] = initPlayerState(
                thisPlayer = currentPlayerId,
                ships = ships
            )
        }
        return GameState(
            gameId = gameId,
            round = 1,
            playersFields = playerStates,
            fieldSize = size,
            history = emptyList(),
            players = playerIds
        )
    }

    private fun initShip(coordinates: List<List<Int>>): Ship =
        Ship(
            coordinates = coordinates.map { (x, y) -> Coordinate(x, y) },
            hits = emptyList(),
            healthPoints = coordinates.size,
            status = ShipStatus.ALIVE
        )

    private fun initPlayerState(
        thisPlayer: UUID,
        ships: List<Ship>
    ): PlayerState {
        return PlayerState(
            player = thisPlayer,
            ships = ships,
            hits = emptyList(),
            misses = emptyList(),
            aliveShips = ships.size
        )
    }
}