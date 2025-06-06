package ru.sigma.security.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("test2")
@RequestMapping("/test2")
class TestController {
    @GetMapping
    fun hello(): String {
        return "Hello, world2!"
    }
}