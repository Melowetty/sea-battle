package ru.sigma.matchmaking.service.impl

import kotlin.random.Random
import org.springframework.stereotype.Component
import ru.sigma.common.model.Coordinate
import ru.sigma.matchmaking.service.ShipPlacementService

@Component
class RandomShipPlacementService : ShipPlacementService {
    override fun getPlaceShips(): List<List<Coordinate>> {
        val battlefield = Array(10) { CharArray(10) { '.' } }
        val ships = listOf(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)
        val result = mutableListOf<List<Coordinate>>()

        for (shipSize in ships) {
            var placed = false
            while (!placed) {
                val row = Random.nextInt(10)
                val col = Random.nextInt(10)
                val horizontal = Random.nextBoolean()

                if (canPlaceShip(battlefield, row, col, shipSize, horizontal)) {
                    val shipCoordinates = mutableListOf<Coordinate>()
                    for (i in 0 until shipSize) {
                        val currentRow = if (horizontal) row else row + i
                        val currentCol = if (horizontal) col + i else col
                        battlefield[currentRow][currentCol] = 'S'
                        shipCoordinates.add(Coordinate(currentCol, currentRow))
                    }
                    result.add(shipCoordinates)
                    placed = true
                }
            }
        }

        return result
    }

    private fun canPlaceShip(
        battlefield: Array<CharArray>,
        row: Int,
        col: Int,
        shipSize: Int,
        horizontal: Boolean
    ): Boolean {
        if (horizontal && col + shipSize > 10) return false
        if (!horizontal && row + shipSize > 10) return false

        for (i in 0 until shipSize) {
            val currentRow = if (horizontal) row else row + i
            val currentCol = if (horizontal) col + i else col
            if (currentRow !in 0..9 || currentCol !in 0..9) return false
            if (battlefield[currentRow][currentCol] != '.') return false
        }

        return true
    }
}