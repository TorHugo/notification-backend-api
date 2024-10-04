package com.dev.notification.backend.api.domain.gateway

import com.dev.notification.backend.api.domain.entity.AuthorityToUserDomain

interface AuthorityToUserGateway {
    fun saveAll(authorities: List<AuthorityToUserDomain>)
}