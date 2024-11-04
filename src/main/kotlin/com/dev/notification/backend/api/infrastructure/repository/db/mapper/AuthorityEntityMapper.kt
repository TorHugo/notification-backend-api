package com.dev.notification.backend.api.infrastructure.repository.db.mapper

import com.dev.notification.backend.api.domain.entity.AuthorityDomain
import com.dev.notification.backend.api.infrastructure.repository.db.models.AuthorityEntity

object AuthorityEntityMapper {
    fun toAggregate(entity: AuthorityEntity): AuthorityDomain {
        return AuthorityDomain.restore(
            entity.identifier,
            entity.name,
            entity.description,
            entity.active,
            entity.createdAt,
            entity.updatedAt,
        )
    }
}
