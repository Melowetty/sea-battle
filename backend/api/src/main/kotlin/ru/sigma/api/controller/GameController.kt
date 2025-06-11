package ru.sigma.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.sigma.api.GameApi

@RequestMapping("/games")
abstract class GameController {
    @Autowired
    private lateinit var gameApi: GameApi

    @GetMapping("/{id}")
    open fun getGame(@PathVariable id: Long): Any {
        return gameApi.getGame(id)
    }

    @PostMapping("/{id}/leave")
    open fun leaveGame(@PathVariable id: Long) {
        gameApi.leaveGame(id)
    }
}