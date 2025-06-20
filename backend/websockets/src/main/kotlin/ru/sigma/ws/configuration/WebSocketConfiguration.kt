package ru.sigma.ws.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.DefaultContentTypeResolver
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.util.MimeTypeUtils
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@ComponentScan("ru.sigma.ws")
@EnableWebSocketMessageBroker
class WebSocketConfiguration: WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic")
        config.setApplicationDestinationPrefixes("/app")
        config.setUserDestinationPrefix("/game")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            .addEndpoint("/ws")
            .setAllowedOriginPatterns("http://localhost", "http://127.0.0.1:5500", "null", "https://d5d5ujno72nh9qu45pq5.sk0vql13.apigw.yandexcloud.net")
            .withSockJS()
    }

    override fun configureMessageConverters(messageConverters: MutableList<MessageConverter>): Boolean {
        val resolver = DefaultContentTypeResolver()
        resolver.defaultMimeType = MimeTypeUtils.APPLICATION_JSON
        val converter = MappingJackson2MessageConverter()
        converter.objectMapper = ObjectMapper()
        converter.contentTypeResolver = resolver
        messageConverters.add(converter)
        return false
    }
}