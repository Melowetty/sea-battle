package ru.sigma.data.domain.model.game

import ru.sigma.data.domain.model.ShipStatus
import java.util.UUID
import ru.sigma.common.model.Coordinate

data class GameState(
    var round: Int, // какой сейчас раунд
    val playersFields: Map<UUID, PlayerState>, // игроки и их поля
    val fieldSize: Int, // размер поля
    var history: List<String>, // история раундов по id
    var players: List<UUID> // список игроков
)

data class PlayerState(
    val player: UUID,
    val ships: List<Ship>, // мои корабли
    var destructions: List<Coordinate>, // уничтоженные корабли по клеткам
    var hits: List<Coordinate>, // попадания по мне (ещё в живые корабли)
    var misses: List<Coordinate>, // промахи помне
    var aliveShips: Int // количество живых кораблей
)

data class Ship(
    var coordinates: List<Coordinate>, // размещение корабля
    var hits: List<Coordinate>, // куда попали в корабль
    var healthPoints: Int, // скольк оосталось непораженных клеток
    var status: ShipStatus
)