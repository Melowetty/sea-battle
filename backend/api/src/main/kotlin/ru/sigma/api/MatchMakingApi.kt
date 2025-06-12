package ru.sigma.api

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Validated
@Component
class MatchMakingApi {
    fun joinRandomGame() {
        throw NotImplementedError("Not implemented")
    }

    fun joinGame(@NotBlank code: String) {
        throw NotImplementedError("Not implemented")
    }

    fun createGame(request: Any) {
        throw NotImplementedError("Not implemented")
    }

    fun startGame(@Min(1) gameId: Long) {
        throw NotImplementedError("Not implemented")
    }
}