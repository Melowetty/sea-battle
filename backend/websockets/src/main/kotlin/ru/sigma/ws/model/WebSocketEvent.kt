package ru.sigma.ws.model

data class WebSocketEvent(
    val type: EventType,
    val data: Any?
)
