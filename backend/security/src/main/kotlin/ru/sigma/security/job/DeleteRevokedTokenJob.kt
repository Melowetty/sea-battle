package ru.sigma.security.job

import java.time.LocalDate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.sigma.data.repository.RevokedTokenRepository

@Component
class DeleteRevokedTokenJob(
    private val revokedTokenRepository: RevokedTokenRepository
) {
    @Scheduled(cron = "\${app.cron.delete-revoked-tokens}")
    fun deleteRevokedTokens() {
        val date = LocalDate.now()
        revokedTokenRepository.deleteByExpiredLessThanEqual(date)
    }
}