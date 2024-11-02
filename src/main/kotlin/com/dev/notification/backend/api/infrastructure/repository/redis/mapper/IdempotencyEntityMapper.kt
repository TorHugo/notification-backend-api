package com.dev.notification.backend.api.infrastructure.repository.redis.mapper

import com.dev.notification.backend.api.domain.entity.IdempotencyDomain
import com.dev.notification.backend.api.infrastructure.repository.redis.models.IdempotencyEntity

object IdempotencyEntityMapper {
    private const val IDEMPOTENCY_PREFIX = "idempotency"

    fun fromAggregate(domain: IdempotencyDomain): IdempotencyEntity {
        return IdempotencyEntity(
            IDEMPOTENCY_PREFIX,
            domain.identifier,
            domain.status,
            domain.result,
            domain.error,
            domain.createdAt
        )
    }

    fun toAggregate(entity: IdempotencyEntity): IdempotencyDomain {
        return IdempotencyDomain.restore(
            entity.key,
            entity.status,
            entity.result,
            entity.error,
            entity.createdAt
        )
    }
}
