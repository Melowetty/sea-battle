package ru.sigma.api

interface GameApi {
    fun getGame(id: Long): Any

    fun leaveGame(id: Long)
}