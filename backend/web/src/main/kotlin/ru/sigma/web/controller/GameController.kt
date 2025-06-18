package ru.sigma.web.controller

import jakarta.validation.constraints.Min
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.sigma.domain.dto.GameDto
import ru.sigma.security.api.SecuredGameApi

@RestController
@RequestMapping("/games")
class GameController(
    private val securedGameApi: SecuredGameApi
) {

    @GetMapping("/{id}")
    fun getGame(@PathVariable @Min(1) id: Long): GameDto {
        return securedGameApi.getGame(id)
    }

    @PostMapping("/{id}/leave")
    fun leaveGame(@PathVariable @Min(1) id: Long) {
        securedGameApi.leaveGame(id)
    }
}