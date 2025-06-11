package ru.sigma.api

import jakarta.validation.constraints.Min
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Validated
@Component
class GameApi {
    fun getGame(@Min(1) id: Long): Any {
        throw NotImplementedError("Not implemented")
    }

    fun leaveGame(@Min(1) id: Long) {
        throw NotImplementedError("Not implemented")
    }
}