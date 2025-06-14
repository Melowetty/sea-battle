package ru.sigma.security.service

import org.springframework.stereotype.Service
import ru.sigma.security.domain.model.telegram.TelegramAuthRequest
import ru.sigma.security.domain.model.AuthTokenResponse

@Service
class AuthService {
    fun authByTelegram(request: TelegramAuthRequest): AuthTokenResponse {
        TODO("Not implemented")
    }
}