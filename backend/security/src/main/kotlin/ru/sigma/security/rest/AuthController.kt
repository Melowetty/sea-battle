package ru.sigma.security.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.sigma.security.domain.model.AuthTokenRefreshRequest
import ru.sigma.security.domain.model.AuthTokenRefreshResponse
import ru.sigma.security.domain.model.telegram.TelegramAuthRequest
import ru.sigma.security.domain.model.AuthTokenResponse
import ru.sigma.security.service.AuthService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/telegram")
    fun telegramAuth(@RequestBody request: TelegramAuthRequest): AuthTokenResponse {
        return authService.authByTelegram(request)
    }

    @PostMapping("/refresh")
    fun telegramAuth(@RequestBody request: AuthTokenRefreshRequest): AuthTokenRefreshResponse {
        return authService.refreshAuthorization(request.refreshToken)
    }
}