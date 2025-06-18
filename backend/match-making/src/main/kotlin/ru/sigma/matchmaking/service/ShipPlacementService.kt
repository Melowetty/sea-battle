package ru.sigma.matchmaking.service

import ru.sigma.common.model.Coordinate

interface ShipPlacementService {
    fun getPlaceShips(): List<List<Coordinate>>
}