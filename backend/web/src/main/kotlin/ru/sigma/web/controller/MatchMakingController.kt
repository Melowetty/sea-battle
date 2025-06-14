package ru.sigma.web.controller

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.sigma.security.api.SecuredMatchMakingApi

@RestController
@Validated
@RequestMapping("/rooms")
class MatchMakingController(
    private val securedMatchMakingApi: SecuredMatchMakingApi
) {

    @PostMapping("/join/random")
    fun joinRandomRoom() {
        return securedMatchMakingApi.joinRandomRoom()
    }

    @PostMapping("/join/{code}")
    fun joinRoom(@PathVariable code: String) {
        return securedMatchMakingApi.joinRoom(code)
    }

    @PostMapping("/create")
    fun createRoom(@RequestBody request: Any) {
        return securedMatchMakingApi.createRoom(request)
    }

    @PostMapping("/{id}/start")
    fun startRoom(@PathVariable @Min(1) id: Long) {
        return securedMatchMakingApi.startRoom(id)
    }

    @PostMapping("/{id}/leave")
    fun leaveRoom(@PathVariable @Min(1) id: Long) {
        return securedMatchMakingApi.leaveRoom(id)
    }
}