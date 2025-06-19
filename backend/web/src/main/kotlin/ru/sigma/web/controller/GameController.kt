package ru.sigma.web.controller

import jakarta.validation.constraints.Min
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.sigma.common.model.Coordinate
import ru.sigma.game.domain.dto.AfterShotStateDto
import ru.sigma.game.domain.dto.GameDto
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

    @PostMapping("/{id}/shot")
    fun shot(@PathVariable @Min(1) id: Long, @RequestBody shot: ShotRequest): AfterShotStateDto {
        return securedGameApi.makeShot(id, Coordinate(shot.x, shot.y))
    }

    @PostMapping("/{id}/leave")
    fun leaveGame(@PathVariable @Min(1) id: Long) {
        securedGameApi.leaveGame(id)
    }

    data class ShotRequest(
        @Min(0)
        val x: Int,
        @Min(0)
        val y: Int,
    )
}