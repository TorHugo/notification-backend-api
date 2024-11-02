package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.entity.AuthorityDomain
import com.dev.notification.backend.api.domain.gateway.AuthorityGateway
import org.springframework.stereotype.Component

@Component
class FindAuthorityByName(
    private val authorityGateway: AuthorityGateway
) {
    fun execute(name: String): AuthorityDomain {
        return authorityGateway.findByName(name)
    }
}
