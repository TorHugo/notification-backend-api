package com.dev.notification.backend.api.infrastructure.gateway

import com.dev.notification.backend.api.domain.entity.AuthorityToUserDomain
import com.dev.notification.backend.api.domain.gateway.AuthorityToUserGateway
import com.dev.notification.backend.api.infrastructure.repository.db.AuthorityToUserRepository
import com.dev.notification.backend.api.infrastructure.repository.db.mapper.AuthorityToUserEntityMapper
import org.springframework.stereotype.Component

@Component
class AuthorityToUserGatewayImpl(
    private val authorityToUserRepository: AuthorityToUserRepository,
) : AuthorityToUserGateway {
    override fun saveAll(authorities: List<AuthorityToUserDomain>) {
        val entities = AuthorityToUserEntityMapper.fromAggregate(authorities)
        authorityToUserRepository.saveAll(entities)
    }
}
