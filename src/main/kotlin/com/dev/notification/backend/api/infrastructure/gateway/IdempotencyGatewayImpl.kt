package com.dev.notification.backend.api.infrastructure.gateway

import com.dev.notification.backend.api.domain.entity.IdempotencyDomain
import com.dev.notification.backend.api.domain.gateway.IdempotencyGateway
import com.dev.notification.backend.api.infrastructure.repository.redis.mapper.IdempotencyEntityMapper
import com.dev.notification.backend.api.infrastructure.repository.redis.models.IdempotencyEntity
import com.dev.notification.backend.api.infrastructure.repository.redis.template.IdempotencyRepository
import org.springframework.stereotype.Component

@Component
class IdempotencyGatewayImpl(
    val idempotencyRepository: IdempotencyRepository,
) : IdempotencyGateway {

    companion object {
        private const val IDEMPOTENCY_PREFIX = "idempotency"
    }

    override fun findByIdentifier(identifier: String): IdempotencyDomain? {
        val entity = idempotencyRepository.get(IDEMPOTENCY_PREFIX, identifier, IdempotencyEntity::class.java)
        return entity?.let { IdempotencyEntityMapper.toAggregate(it) }
    }

    override fun save(domain: IdempotencyDomain) {
        val entity = IdempotencyEntityMapper.fromAggregate(domain)
        idempotencyRepository.save(entity)
    }
}
