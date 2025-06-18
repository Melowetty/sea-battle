package ru.sigma.matchmaking.exception

import ru.sigma.common.exception.NotFoundException

class RoomNotFoundException(message: String) : NotFoundException(message)
class RoomIsFullException(message: String) : RuntimeException(message)