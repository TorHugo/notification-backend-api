package com.dev.notification.backend.api.infrastructure.repository.db.mapper

import com.dev.notification.backend.api.domain.entity.AuthorityToUserDomain
import com.dev.notification.backend.api.infrastructure.repository.db.models.AuthorityToUserEntity
import com.dev.notification.backend.api.infrastructure.repository.db.models.CompositeKey

object AuthorityToUserEntityMapper {
    private fun fromAggregate(domain: AuthorityToUserDomain): AuthorityToUserEntity {
        val compositeKey = CompositeKey(domain.getUserIdentifier(), domain.getAuthorityIdentifier())
        return AuthorityToUserEntity(
            compositeKey,
            domain.createdAt,
        )
    }

    fun fromAggregate(domains: List<AuthorityToUserDomain>): List<AuthorityToUserEntity> {
        return domains.map { domain -> fromAggregate(domain) }
    }
}
