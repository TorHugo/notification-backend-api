package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.exception.template.DomainException
import com.dev.notification.backend.api.domain.gateway.UserGateway
import com.dev.notification.backend.api.infrastructure.security.models.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl(
    private val userGateway: UserGateway,
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) throw DomainException("This email is not valid!")
        val user = userGateway.findByEmailWithThrows(username)
        return UserDetailsImpl.build(user, arrayListOf())
    }
}
