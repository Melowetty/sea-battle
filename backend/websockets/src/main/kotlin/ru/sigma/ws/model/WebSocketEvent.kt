package ru.sigma.ws.model

data class WebSocketEvent(
    val type: String,
    val data: Any?
)
