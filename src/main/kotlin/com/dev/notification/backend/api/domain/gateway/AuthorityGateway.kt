package com.dev.notification.backend.api.domain.gateway

import com.dev.notification.backend.api.domain.entity.AuthorityDomain

interface AuthorityGateway {
    fun findByName(name: String): AuthorityDomain
}
