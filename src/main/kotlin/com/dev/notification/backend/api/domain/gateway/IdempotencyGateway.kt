package com.dev.notification.backend.api.domain.gateway

import com.dev.notification.backend.api.domain.entity.IdempotencyDomain

interface IdempotencyGateway {
    fun findByIdentifier(identifier: String): IdempotencyDomain?
    fun save(domain: IdempotencyDomain)
}
