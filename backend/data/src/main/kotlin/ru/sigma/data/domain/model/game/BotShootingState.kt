package ru.sigma.data.domain.model.game

import ru.sigma.common.model.Coordinate

data class BotShootingState(
    val hits: Set<Coordinate>,        // Все попадания (активные + убитые)
    val misses: Set<Coordinate>,      // Промахи
    val fieldSize: Int              // Размер поля N x N
)