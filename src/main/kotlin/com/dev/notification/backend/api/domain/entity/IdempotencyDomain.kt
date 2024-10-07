package com.dev.notification.backend.api.domain.entity

import com.dev.notification.backend.api.domain.enums.IdempotencyStatus
import java.time.LocalDateTime

data class IdempotencyDomain(
    val identifier: String,
    val status: IdempotencyStatus,
    val result: String? = null,
    val error: String? = null,
    val createdAt: LocalDateTime
) {
    companion object {
        fun create(identifier: String, status: IdempotencyStatus): IdempotencyDomain {
            return IdempotencyDomain(
                identifier = identifier,
                status = status,
                createdAt = LocalDateTime.now()
            )
        }

        fun restore(identifier: String, status: IdempotencyStatus, result: String?, error: String?, createdAt: LocalDateTime): IdempotencyDomain {
            return IdempotencyDomain(identifier, status, result, error, createdAt)
        }
    }
}