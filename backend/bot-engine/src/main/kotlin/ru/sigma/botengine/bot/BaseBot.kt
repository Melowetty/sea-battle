package ru.sigma.botengine.bot

import ru.sigma.common.model.Coordinate
import ru.sigma.common.model.GameBot
import ru.sigma.data.domain.model.game.BotShootingState

interface BaseBot {
    fun process(state: BotShootingState): Coordinate
    fun getBot(): GameBot
}