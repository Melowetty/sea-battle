package ru.sigma.data.extensions

import ru.sigma.common.dto.UserDto
import ru.sigma.data.domain.entity.UserEntity

object UserExtensions {
    fun UserEntity.toDto() = UserDto(
        id = id,
        name = name,
        avatar = avatar,
        isBot = isBot
    )
}