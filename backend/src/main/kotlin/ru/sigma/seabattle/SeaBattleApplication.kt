package ru.sigma.seabattle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("ru.sigma.api")
@ComponentScan("ru.sigma.data")
@ComponentScan("ru.sigma.security")
class SeaBattleApplication

fun main(args: Array<String>) {
    runApplication<SeaBattleApplication>(*args)
}
