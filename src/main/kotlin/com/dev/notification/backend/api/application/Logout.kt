package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.infrastructure.security.jwt.JwtTokenUtils
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component

@Component
class Logout(
    private val jwtTokenUtils: JwtTokenUtils,
) {
    fun execute(): ResponseCookie {
        return jwtTokenUtils.cleanToken()
    }
}
