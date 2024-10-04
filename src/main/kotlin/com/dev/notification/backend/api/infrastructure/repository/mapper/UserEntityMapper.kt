package com.dev.notification.backend.api.infrastructure.repository.mapper

import com.dev.notification.backend.api.domain.entity.UserDomain
import com.dev.notification.backend.api.infrastructure.repository.models.UserEntity

object UserEntityMapper {
    fun fromAggregate(domain: UserDomain): UserEntity {
        return UserEntity(
            identifier = domain.getIdentifier(),
            firstName = domain.firstName,
            lastName = domain.lastName,
            email = domain.email.value,
            password = domain.password,
            active = domain.active,
            admin = domain.admin,
            confirmed = domain.confirmed,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt,
            lastAccess = domain.lastAccess
        )
    }

    fun toAggregate(entity: UserEntity): UserDomain {
        return UserDomain.restore(
            identifier = entity.identifier,
            firstName = entity.firstName,
            lastName = entity.lastName,
            email = entity.email,
            password = entity.password,
            active = entity.active,
            admin = entity.admin,
            confirmed = entity.confirmed,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
            lastAccess = entity.lastAccess
        )
    }
}
