package ru.sigma.data.repository;

import java.time.Instant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sigma.data.domain.entity.RoomEntity

@Repository
interface RoomRepository : JpaRepository<RoomEntity, String> {
    fun deleteByCreatedAtLessThanEqual(expired: Instant)
    fun findByIsPublic(isPublic: Boolean): List<RoomEntity>
}