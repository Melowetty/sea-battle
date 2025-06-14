package ru.sigma.data.repository

import java.time.LocalDate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sigma.data.domain.entity.RevokedTokenEntity

@Repository
interface RevokedTokenRepository : JpaRepository<RevokedTokenEntity, String> {
    fun deleteByExpiredLessThanEqual(expired: LocalDate)
    fun existsByToken(token: String): Boolean
}