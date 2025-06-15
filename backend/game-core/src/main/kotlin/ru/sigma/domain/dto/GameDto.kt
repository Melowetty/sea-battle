package ru.sigma.domain.dto

import ru.sigma.data.domain.model.game.PlayerState
import java.time.Instant
import java.util.UUID

class GameDto(
    val players: List<PlayerDto>,
    val currentPlayer: UUID,
    val playerState: PlayerState,
    val gameStartDate: Instant
)

class PlayerDto(
    val name: String,
    val avatar: String,
)