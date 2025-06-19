package ru.sigma.data.domain.model.game

import ru.sigma.common.model.Coordinate

data class BotShootingState(
    val hits: List<Coordinate>,        // все попадания (без уничтожений)
    val misses: List<Coordinate>,      // промахи (с уничтодениями)
    val fieldSize: Int
)