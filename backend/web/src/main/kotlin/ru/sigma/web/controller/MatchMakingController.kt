package ru.sigma.web.controller

import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework  .web.bind.annotation.RestController
import ru.sigma.common.dto.RoomDto
import ru.sigma.game.domain.dto.GameDto
import ru.sigma.security.api.SecuredMatchMakingApi

@RestController
@Validated
@RequestMapping("/rooms")
class MatchMakingController(
    private val securedMatchMakingApi: SecuredMatchMakingApi
) {

    @PostMapping("/join/random")
    fun joinRandomRoom(): RoomDto {
        return securedMatchMakingApi.joinRandomRoom()
    }

    @PostMapping("/join/{code}")
    fun joinRoom(@PathVariable code: String): RoomDto {
        return securedMatchMakingApi.joinRoom(code)
    }

    @PostMapping("/create")
    fun createRoom(): RoomDto {
        return securedMatchMakingApi.createRoom()
    }

    @PostMapping("/{code}/start")
    fun startRoom(@PathVariable @NotBlank code: String): GameDto {
        return securedMatchMakingApi.startRoom(code)
    }

    @PostMapping("/{code}/start/bots")
    fun startRoomWithBots(@PathVariable @NotBlank code: String): GameDto {
        return securedMatchMakingApi.startRoomWithBots(code)
    }

    @PostMapping("/{code}/leave")
    fun leaveRoom(@PathVariable @NotBlank code: String) {
        return securedMatchMakingApi.leaveRoom(code)
    }
}