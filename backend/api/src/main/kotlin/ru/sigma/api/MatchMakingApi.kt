package ru.sigma.api

interface MatchMakingApi {
    fun joinRandomRoom()

    fun joinRoom(code: String)

    fun createRoom(request: Any)

    fun startRoom(roomId: Long)

    fun leaveRoom(roomId: Long)
}