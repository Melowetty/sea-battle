package ru.sigma.gamecore.service

import ru.sigma.data.domain.model.ShipStatus
import ru.sigma.data.domain.model.game.Coordinate
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.domain.model.game.PlayerState
import ru.sigma.data.domain.model.game.Ship
import java.util.UUID

class InitService {

    fun initShip(coordinates: List<List<Int>>): Ship {
        var allCoordinate: List<Coordinate> = emptyList()
        coordinates.forEach {
            allCoordinate += Coordinate(it[0],it[1])
        }
        return Ship(
            coordinates = allCoordinate,
            hits = emptyList(),
            healthPoints = allCoordinate.size,
            status = ShipStatus.ALIVE
        )
    }

    fun initPlayerState(
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

    fun initGame(
        gameId: Long,
        players: Map<UUID, PlayerState>,
        size: Int
    ): GameState {
        val initGame = GameState(
            gameId = gameId,
            activePlayer = players.keys.elementAt(0),
            targetPlayer = players.keys.elementAt(1),
            playersFields = players,
            fieldSize = size
        )
        return initGame
    }
}