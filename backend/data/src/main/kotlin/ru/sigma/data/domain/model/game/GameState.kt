package ru.sigma.data.domain.model.game

import ru.sigma.data.domain.model.GameStatus
import ru.sigma.data.domain.model.ShipStatus
import java.util.UUID

data class GameState(
    val gameId: Long,
    val activePlayer: UUID,
    val targetPlayer: UUID,
    val playersFields: Map<UUID, PlayerState>, // игроки и их поля
    val fieldSize: Int, // размер поля
)

data class PlayerState(
    val player: UUID,
    val ships: List<Ship>, // мои корабли
    var hits: List<Coordinate>, // попадания по мне
    val misses: List<Coordinate>, // промахи помне
    val aliveShips: Int // количество живых кораблей
)

//data class FieldState(
//
//)
//
//data class RadarState(
//    val player: UUID,
//    val misses: List<Coordinate>, // мои промахи по врагу
//    val hits: List<Coordinate> // мои попадания по врагу
//)

data class Ship(
    var coordinates: List<Coordinate>, // размещение корабля
    var hits: List<Coordinate>, // куда попали в корабль
    var healthPoints: Int, // скольк оосталось непораженных клеток
    var status: ShipStatus
)

data class Coordinate(
    val x: Int,
    val y: Int
)