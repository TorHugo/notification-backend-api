package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.gateway.UserGateway
import org.springframework.stereotype.Component

@Component
class UpdateLastAccess(
    private val userGateway: UserGateway
){
    fun execute(email: String){
        val userDomain = userGateway.findByEmailWithThrows(email)
        userDomain.updateLastAccess()
        userGateway.save(userDomain)
    }
}