package ru.sigma.security.domain.exception

class TokenIsExpiredException(
    message: String
): RuntimeException(message)