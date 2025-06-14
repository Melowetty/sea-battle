package ru.sigma.security.configuration

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Configuration
@EnableWebSecurity
@ComponentScan("ru.sigma.security")
class SecurityConfiguration {
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { it.disable() }
//            .cors { it.disable() }
//            .authorizeHttpRequests { auth ->
//                auth
//                    .requestMatchers(
//                        "/auth/**",
//                        "/actuator/**"
//                    ).permitAll()
//                    .anyRequest().authenticated()
//            }
//            .formLogin { form ->
//                form.disable()
//            }
//            .logout { logout ->
//                logout.disable()
//            }
//
//        return http.build()
//    }
}