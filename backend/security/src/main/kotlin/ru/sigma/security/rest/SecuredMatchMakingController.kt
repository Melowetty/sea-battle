package ru.sigma.security.rest

import org.springframework.web.bind.annotation.RestController
import ru.sigma.api.rest.MatchMakingController

@RestController
class SecuredMatchMakingController : MatchMakingController() {
    override fun joinRandomGame() {
        println("from secured")
        return super.joinRandomGame()
    }

    override fun joinGame(code: String) {
        println("from secured")
        return super.joinGame(code)
    }

    override fun createGame(request: Any) {
        println("from secured")
        return super.createGame(request)
    }

    override fun startGame(id: Long) {
        println("from secured")
        return super.startGame(id)
    }
}