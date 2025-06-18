package ru.sigma.game.service

import ru.sigma.data.domain.model.ShipStatus
import ru.sigma.data.domain.model.game.GameState
import ru.sigma.data.domain.model.game.PlayerState
import ru.sigma.data.domain.model.game.Ship
import java.util.UUID
import org.springframework.stereotype.Service
import ru.sigma.common.model.Coordinate

@Service
class InitService {

    fun initGameState(
        players: Map<UUID, List<List<Coordinate>>>,
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
            round = 1,
            playersFields = playerStates,
            fieldSize = size,
            history = emptyList(),
            players = playerIds
        )
    }

    private fun initShip(coordinates: List<Coordinate>): Ship =
        Ship(
            coordinates = coordinates,
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
            destructions = emptyList(),
            hits = emptyList(),
            misses = emptyList(),
            aliveShips = ships.size
        )
    }
}