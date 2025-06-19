package ru.sigma.web.configuration

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker

@Configuration
@ComponentScan("ru.sigma.ws")
@EnableWebSocketMessageBroker
class WebSocketConfiguration