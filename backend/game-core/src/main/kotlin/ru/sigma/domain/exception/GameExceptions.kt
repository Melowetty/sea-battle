package ru.sigma.domain.exception

import ru.sigma.common.exception.NotFoundException

class GameNotFoundException(message: String) : NotFoundException(message)