package ru.sigma.web.model

import org.springframework.http.HttpStatus

open class ResponseStatusException(
    val status: HttpStatus,
    message: String,
): RuntimeException(message)