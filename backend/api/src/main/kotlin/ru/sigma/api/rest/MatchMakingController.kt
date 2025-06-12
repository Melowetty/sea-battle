package ru.sigma.api.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import ru.sigma.api.MatchMakingApi

@RequestMapping("/match-making")
abstract class MatchMakingController {
    @Autowired
    private lateinit var matchMakingApi: MatchMakingApi

    @PostMapping("/join/random")
    open fun joinRandomGame() {
        return matchMakingApi.joinRandomGame()
    }

    @PostMapping("/join/{code}")
    open fun joinGame(@PathVariable code: String) {
        return matchMakingApi.joinGame(code)
    }

    @PostMapping("/create")
    open fun createGame(@RequestBody request: Any) {
        return matchMakingApi.createGame(request)
    }

    @PostMapping("/{id}/start")
   open fun startGame(@PathVariable id: Long) {
        return matchMakingApi.startGame(id)
    }
}