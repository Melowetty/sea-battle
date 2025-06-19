package ru.sigma.security.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.UUID
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.sigma.common.model.UserInfo
import ru.sigma.data.domain.entity.RevokedTokenEntity
import ru.sigma.data.repository.RevokedTokenRepository
import ru.sigma.security.domain.exception.TokenIsExpiredException
import ru.sigma.security.domain.model.TokenInfo

@Service
class JwtService(
    private val revokedTokenRepository: RevokedTokenRepository
) {
    @Value("\${spring.security.jwt.private-key}")
    private lateinit var privateKey: String

    @Value("\${spring.security.jwt.access-token.live-time}")
    private lateinit var accessTokenLiveTime: Duration

    @Value("\${spring.security.jwt.refresh-token.live-time}")
    private lateinit var refreshTokenLiveTime: Duration

    fun generateAccessToken(
        user: UserInfo
    ): TokenInfo {
        return generateToken(user, accessTokenLiveTime)
    }

    fun generateRefreshToken(
        user: UserInfo
    ): TokenInfo {
        return generateToken(user, refreshTokenLiveTime)
    }

    fun verifyToken(token: String): Boolean {
        val isRevoked = revokedTokenRepository.existsByToken(token)
        return !isRevoked && !isTokenExpired(token)
    }

    fun invalidateToken(token: String) {
        getClaims(token)

        val entity = RevokedTokenEntity(
            token = token,
            expired = extractExpirationDate(token).toLocalDate()
        )

        revokedTokenRepository.save(entity)
    }

    private fun generateToken(user: UserInfo, liveTime: Duration): TokenInfo {
        val claims = makeClaims(user)

        val token = Jwts
            .builder()
            .setClaims(claims)
            .setSubject(user.id.toString())
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + liveTime.toMillis()))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()

        return TokenInfo(
            token = token,
            expiresIn = liveTime.toSeconds()
        )
    }

    fun getUserInfoFromToken(token: String): UserInfo {
        val claims = getClaims(token)

        return UserInfo(
            id = UUID.fromString(claims["id"] as String),
            telegramId = (claims["telegram_id"] as String).toLong(),
        )
    }

    private fun makeClaims(userInfo: UserInfo): Map<String, Any?> {
        return mapOf(
            "id" to userInfo.id,
            "telegram_id" to userInfo.telegramId.toString(),
        )
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpirationDate(token)
            .isBefore(LocalDateTime.now())
    }

    private fun extractExpirationDate(token: String): LocalDateTime {
        val claims: Claims = getClaims(token)
        return claims
            .expiration
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .body
        } catch (exception: JwtException) {
            throw TokenIsExpiredException("Токен недействителен")
        }
    }

    private fun getSignInKey(): Key {
        val decoded = Decoders.BASE64.decode(privateKey)
        return Keys.hmacShaKeyFor(decoded)
    }
}