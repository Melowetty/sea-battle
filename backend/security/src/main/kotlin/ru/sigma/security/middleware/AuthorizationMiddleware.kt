package ru.sigma.security.middleware

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.server.ResponseStatusException
import ru.sigma.common.context.UserAuthContextHolder
import ru.sigma.security.service.AuthService

@Component
@Order(-1000)
class AuthorizationMiddleware(
    private val authService: AuthService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val headers = request.headerNames.toList().toSet()
        val authHeader = headers.find { it == HttpHeaders.AUTHORIZATION }
        authHeader?.let {
            val token = request.getHeader(it).replace("Bearer ", "")
            val isValid = authService.validateAuthToken(token)
            if (!isValid) {
                throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token")
            }

            val userAuthContext = authService.extractUserAuthContext(token)
            UserAuthContextHolder.set(userAuthContext)

            val authentication = OneTimeTokenAuthenticationToken.authenticated(userAuthContext, listOf())
            val context = SecurityContextImpl(authentication)
            SecurityContextHolder.setContext(context)
        }

        filterChain.doFilter(request, response)
    }
}