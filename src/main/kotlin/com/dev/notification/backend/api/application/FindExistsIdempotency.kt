package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.entity.IdempotencyDomain
import com.dev.notification.backend.api.domain.gateway.IdempotencyGateway
import org.springframework.stereotype.Component

@Component
class FindExistsIdempotency(
    val idempotencyGateway: IdempotencyGateway
) {
    fun execute(identifier: String): IdempotencyDomain? {
        val existsIdempotencyWithIdentifier = idempotencyGateway.findByIdentifier(identifier)
        return existsIdempotencyWithIdentifier
    }
}