package ru.sigma.data.extensions

import ru.sigma.common.dto.RoomDto
import ru.sigma.data.domain.entity.RoomEntity
import ru.sigma.data.extensions.UserExtensions.toDto

object RoomExtensions {
    fun RoomEntity.toDto() = RoomDto(
        code = code,
        host = host.toDto(),
        players = players.map { it.toDto() },
        maxSize = maxSize,
        isPublic = isPublic,
    )
}