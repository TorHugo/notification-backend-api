package com.dev.notification.backend.api.domain.gateway

import com.dev.notification.backend.api.domain.entity.UserDomain

interface UserGateway {
    fun save(user: UserDomain) : UserDomain
    fun findByEmail(email: String): UserDomain?
    fun findByEmailWithThrows(email: String): UserDomain
}