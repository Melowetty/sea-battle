package ru.sigma.web.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.sigma.security.domain.exception.TokenIsExpiredException
import ru.sigma.web.model.ErrorResponse

@RestControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(TokenIsExpiredException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTokenExpiredException(e: TokenIsExpiredException): ErrorResponse {
        return ErrorResponse(e.message ?: "", "TOKEN_EXPIRED")
    }
}