package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.entity.UserDomain
import com.dev.notification.backend.api.domain.gateway.UserGateway
import org.springframework.stereotype.Component

@Component
class SaveUser(
    private val userGateway: UserGateway
) {
    fun execute(userDomain: UserDomain) {
        userGateway.save(userDomain)
    }
}