package ru.sigma.game.domain.dto

import ru.sigma.data.domain.model.game.PlayerState
import java.time.Instant
import java.util.UUID
import ru.sigma.common.dto.UserDto

class GameDto(
    val players: List<UserDto>,
    val currentPlayer: UUID,
    val playerState: PlayerState,
    val gameStartDate: Instant
)