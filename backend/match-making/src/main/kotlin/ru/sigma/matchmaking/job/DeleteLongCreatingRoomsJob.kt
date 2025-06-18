package ru.sigma.matchmaking.job

import java.time.Instant
import java.time.temporal.ChronoUnit
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.sigma.data.repository.RoomRepository

@Component
class DeleteLongCreatingRoomsJob(
    private val roomRepository: RoomRepository
) {
    @Scheduled(cron = "\${app.cron.delete-expired-rooms}")
    fun deleteLongCreatingRooms() {
        val currentTime = Instant.now()
        val expiredTime = currentTime.minus(1, ChronoUnit.HOURS)

        roomRepository.deleteByCreatedAtLessThanEqual(expiredTime)
    }
}