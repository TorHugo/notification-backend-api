package com.dev.notification.backend.api.infrastructure.gateway

import com.dev.notification.backend.api.domain.entity.AuthorityDomain
import com.dev.notification.backend.api.domain.exception.template.GatewayException
import com.dev.notification.backend.api.domain.gateway.AuthorityGateway
import com.dev.notification.backend.api.infrastructure.repository.db.AuthorityRepository
import com.dev.notification.backend.api.infrastructure.repository.db.mapper.AuthorityEntityMapper
import org.springframework.stereotype.Component

@Component
class AuthorityGatewayImpl(
    private val authorityRepository: AuthorityRepository
) : AuthorityGateway {

    override fun findByName(name: String): AuthorityDomain {
        val authority = authorityRepository.findByName(name) ?: throw GatewayException("Authority not found with name!", name)
        return AuthorityEntityMapper.toAggregate(authority)
    }
}