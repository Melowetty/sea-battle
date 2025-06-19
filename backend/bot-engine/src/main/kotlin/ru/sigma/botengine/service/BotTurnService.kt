package ru.sigma.botengine.service

import java.util.UUID
import org.springframework.stereotype.Service
import ru.sigma.botengine.bot.BaseBot
import ru.sigma.common.model.Coordinate
import ru.sigma.data.domain.model.game.BotShootingState

@Service
class BotTurnService(
    private val bots: List<BaseBot>
) {
    private val botMap = bots.associateBy { it.getBot().id }

    fun processShot(
        state: BotShootingState,
        botId: UUID
    ): Coordinate {
        val botInstance = botMap[botId]
        checkNotNull(botInstance) {
            "Player is not bot"
        }

        return botInstance.process(state)
    }
}
