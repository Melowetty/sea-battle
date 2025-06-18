package ru.sigma.seabattle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SeaBattleApplication

fun main(args: Array<String>) {
    runApplication<SeaBattleApplication>(*args)
}
