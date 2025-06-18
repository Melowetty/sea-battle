package ru.sigma.common.dto

data class RoomDto(
    val code: String,
    val host: UserDto,
    val players: List<UserDto>,
    val maxSize: Int,
    val isPublic: Boolean,
)
