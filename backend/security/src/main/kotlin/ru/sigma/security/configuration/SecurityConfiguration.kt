package ru.sigma.security.configuration

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableWebSecurity
@Configuration
@ComponentScan("ru.sigma.security")
class SecurityConfiguration