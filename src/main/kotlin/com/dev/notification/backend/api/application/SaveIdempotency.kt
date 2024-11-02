package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.entity.IdempotencyDomain
import com.dev.notification.backend.api.domain.gateway.IdempotencyGateway
import org.springframework.stereotype.Component

@Component
class SaveIdempotency(
    val idempotencyGateway: IdempotencyGateway
) {
    fun execute(domain: IdempotencyDomain) {
        idempotencyGateway.save(domain)
    }
}
