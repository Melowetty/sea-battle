package ru.sigma.data.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan("ru.sigma.data")
@EnableJpaRepositories("ru.sigma.data.repository")
@EntityScan("ru.sigma.data.domain.entity")
class DataConfiguration