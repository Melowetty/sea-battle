package ru.sigma.ws.listener

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import ru.sigma.game.domain.event.GameFinishEvent
import ru.sigma.game.domain.event.GameTurnEvent
import ru.sigma.ws.model.EventType
import ru.sigma.ws.model.WebSocketEvent
import ru.sigma.ws.service.NotifyInGameUsersService

@Component
class GameEventsListener(
    private val notifyService: NotifyInGameUsersService
) {
    @EventListener(GameTurnEvent::class)
    fun handleGameTurnEvent(event: GameTurnEvent) {
        notifyService.notify(event.gameId, WebSocketEvent(EventType.SHOT, event.event))
    }

    @EventListener(GameFinishEvent::class)
    fun handleGameFinishEvent(event: GameFinishEvent) {
        notifyService.notify(event.gameId, WebSocketEvent(EventType.GAME_END, mapOf("winnerId" to event.winner)))
    }
}