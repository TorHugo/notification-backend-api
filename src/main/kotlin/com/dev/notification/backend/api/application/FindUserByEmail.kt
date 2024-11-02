package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.entity.UserDomain
import com.dev.notification.backend.api.domain.gateway.UserGateway
import org.springframework.stereotype.Component

@Component
class FindUserByEmail(
    private val userGateway: UserGateway
) {
    fun execute(email: String): UserDomain? {
        return userGateway.findByEmail(email)
    }
}
