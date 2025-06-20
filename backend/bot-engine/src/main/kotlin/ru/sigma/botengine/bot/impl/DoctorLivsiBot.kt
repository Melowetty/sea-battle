package ru.sigma.botengine.bot.impl

import org.springframework.stereotype.Component
import ru.sigma.botengine.bot.BaseBot
import ru.sigma.common.model.Coordinate
import ru.sigma.common.model.GameBot
import ru.sigma.data.domain.model.CellStatus
import ru.sigma.data.domain.model.game.BotShootingState

@Component
class DoctorLivsiBot: BaseBot {
    override fun process(state: BotShootingState): Coordinate {
        return getRandomShot(state)
//        if (state.hits.isEmpty()) { // если нет попаданий по живым кораблям
//            return getRandomShot(state)
//        }

//        if (isLineExist(state)) { // если есть линия из попаданий
//            return getLineShotCoordinate(state)
//        }
//        return getNeighborShotCoordinate(state) // стреляем рядом с любым попаданием
    }

    override fun getBot(): GameBot {
        return GameBot.DOCTOR_LIVSI
    }

    private fun getRandomShot(state: BotShootingState): Coordinate {
        val available = mutableListOf<Coordinate>()
        val size = state.fieldSize
        for (x in 0 until size) {
            for (y in 0 until size) {
                val coord = Coordinate(x, y)
                if (coord !in state.hits && coord !in state.misses) {
                    available.add(coord)
                }
            }
        }
        return available.random()
    }

    private fun getNeighborShotCoordinate(
        state: BotShootingState
    ): Coordinate {
        var shot = Coordinate(-1, -1)
        for (hit in state.hits) {
            val neighbors = listOf(
                Coordinate(hit.x, hit.y - 1),
                Coordinate(hit.x + 1, hit.y),
                Coordinate(hit.x, hit.y + 1),
                Coordinate(hit.x - 1, hit.y)
            )
            for (coord in neighbors) {
                if (getCellStatus(coord, state) == CellStatus.EMPTY) {
                    shot = coord
                    break
                }
            }
        }
        return shot
    }

    private fun getLineShotCoordinate(
        state: BotShootingState
    ): Coordinate {
        var shot = Coordinate(-1,-1)
        for (hit in state.hits) {
            for (direction in listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)) {
                var current = hit
                while (true) {
                    current = Coordinate(current.x + direction.first, current.y + direction.second)

                    when {
                        !current.isValid(state.fieldSize) -> break
                        current in state.misses -> break
                        current in state.hits -> {
                            val opposite = (Coordinate(current.x - direction.first, current.y - direction.second))
                            if (opposite in state.hits) break // если напротив тоже выстрел, то проверяем дальше
                            shot = opposite // нашли место для стрельбы
                        }
                    }
                }
            }
        }
        return shot
    }
    private fun isLineExist(
        state: BotShootingState
    ): Boolean {
        for (hit in state.hits) {
            for (direction in listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)) {
                var current = hit
                while (true) {
                    current = Coordinate(current.x + direction.first, current.y + direction.second)
                    when {
                        current in state.hits -> return true // если нашли следующее попадание
                        else -> continue  // иначе продолжаем искать рядом стоящие попадания
                    }
                }
            }
        }
        return false
    }

    fun getCellStatus(pos: Coordinate?, state: BotShootingState): CellStatus {
        return when {
            pos == null -> CellStatus.BORDER
            !pos.isValid(state.fieldSize) -> CellStatus.BORDER
            pos in state.hits -> CellStatus.HIT
            pos in state.misses -> CellStatus.MISS
            else -> CellStatus.EMPTY
        }
    }

    private fun Coordinate.isValid(fieldSize: Int) =
        x in 0 until fieldSize && y in 0 until fieldSize
}