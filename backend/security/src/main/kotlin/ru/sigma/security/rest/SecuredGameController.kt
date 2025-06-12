package ru.sigma.security.rest

import org.springframework.web.bind.annotation.RestController
import ru.sigma.api.rest.GameController

@RestController
class SecuredGameController : GameController() {
    override fun getGame(id: Long): Any {
        println("from secured")
        return super.getGame(id)
    }

    override fun leaveGame(id: Long) {
        println("from secured")
        super.leaveGame(id)
    }
}