package ru.sigma.ws.service

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import ru.sigma.ws.model.WebSocketEvent

@Service
class NotifyInGameUsersService(
    private val messagingTemplate: SimpMessagingTemplate
) {
    fun notify(gameId: Long, message: WebSocketEvent) {
        messagingTemplate.convertAndSend("$GAME_TOPIC/$gameId", message)
    }

    companion object {
        private const val GAME_TOPIC = "/topic/game"
    }
}