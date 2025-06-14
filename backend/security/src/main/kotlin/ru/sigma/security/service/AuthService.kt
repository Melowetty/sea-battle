package ru.sigma.security.service

import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.sigma.data.domain.entity.UserEntity
import ru.sigma.data.repository.UserRepository
import ru.sigma.security.Extensions.toUserInfo
import ru.sigma.security.domain.model.AuthTokenResponse
import ru.sigma.security.domain.model.telegram.TelegramAuthRequest

@Service
class AuthService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    @Value("\${spring.security.telegram.bot-token}")
    private val telegramBotToken: String
) {
    fun authByTelegram(request: TelegramAuthRequest): AuthTokenResponse {
        require(!validateTelegramAuthRequest(request)) {
            "Invalid telegram auth request"
        }

        val user = getOrCreateUserEntity(request.id, request.firstName, request.photoUrl)

        val userInfo = user.toUserInfo()

        val accessToken = jwtService.generateAccessToken(userInfo)
        val refreshToken = jwtService.generateRefreshToken(userInfo)

        return AuthTokenResponse(
            accessToken = accessToken.token,
            accessTokenExpiresIn = accessToken.expiresIn,
            refreshToken = refreshToken.token,
            refreshTokenExpiresIn = refreshToken.expiresIn
        )
    }

    private fun getOrCreateUserEntity(telegramId: Long, name: String, avatar: String): UserEntity {
        val user = userRepository.findByTelegramId(telegramId)
        if (user != null) {
            return user
        }

        val entity = UserEntity(
            telegramId = telegramId,
            name = name,
            avatar = avatar,
            isBot = false,
        )

        return userRepository.save(entity)
    }

    private fun validateTelegramAuthRequest(request: TelegramAuthRequest): Boolean {
        val data = mapOf(
            "id" to request.id,
            "first_name" to request.firstName,
            "last_name" to request.lastName,
            "username" to request.username,
            "photo_url" to request.photoUrl,
            "auth_date" to request.authDate.toString()
        )

        val tenMinutesAgo = Instant.now().minus(10, ChronoUnit.MINUTES)
        if (Instant.ofEpochSecond(request.authDate).isBefore(tenMinutesAgo)) return false

        val sortedData = data.entries.sortedBy { it.key }
        val checkString = sortedData.joinToString("\n") { "${it.key}=${it.value}" }
        val checkHash = generateTelegramHash(checkString, telegramBotToken)

        return checkHash == request.hash
    }

    private fun generateTelegramHash(data: String, botToken: String): String {
        val hmac = Mac.getInstance("HmacSHA256").apply {
            val secretKey = SecretKeySpec(botToken.toByteArray(), "HmacSHA256")
            init(secretKey)
        }
        val hashBytes = hmac.doFinal(data.toByteArray())
        return hashBytes.fold("") { str, it -> str + "%02x".format(it) }
    }
}