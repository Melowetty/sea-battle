package ru.sigma.seabattle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SeaBattleApplication

fun main(args: Array<String>) {
    runApplication<SeaBattleApplication>(*args)
}
