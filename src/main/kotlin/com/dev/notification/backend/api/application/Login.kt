package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.infrastructure.security.jwt.JwtTokenUtils
import com.dev.notification.backend.api.infrastructure.security.models.UserDetailsImpl
import org.springframework.http.ResponseCookie
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class Login(
    private val authenticationManager: AuthenticationManager,
    private val updateLastAccess: UpdateLastAccess,
    private val jwtTokenUtils: JwtTokenUtils
) {
    fun execute(email: String, password: String): ResponseCookie {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(email, password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        val userDetails = authentication.principal as UserDetailsImpl
        val cookie = jwtTokenUtils.generateToken(userDetails)
        updateLastAccess.execute(email)
        return cookie
    }
}
