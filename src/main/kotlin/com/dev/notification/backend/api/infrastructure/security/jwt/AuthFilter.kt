package com.dev.notification.backend.api.infrastructure.security.jwt

import com.dev.notification.backend.api.application.UserDetailsServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter(
    private val jwtTokenUtils: JwtTokenUtils,
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = jwtTokenUtils.getTokenFromCookie(request)
            if (token != null && jwtTokenUtils.validateToken(token)) {
                val email = jwtTokenUtils.getUserName(token)
                val loadedUser = userDetailsServiceImpl.loadUserByUsername(email)

                if (!loadedUser.isEnabled) throw AccessDeniedException("User is not active!")

                val authentication = UsernamePasswordAuthenticationToken(
                    loadedUser, null, loadedUser.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            logger.error("Cannot set user authentication.")
        }

        filterChain.doFilter(request, response)
    }

}