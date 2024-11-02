package com.dev.notification.backend.api.domain.entity

import com.dev.notification.backend.api.domain.enums.IdempotencyStatusEnum
import java.time.LocalDateTime

data class IdempotencyDomain(
    val identifier: String,
    val status: IdempotencyStatusEnum,
    val result: String? = null,
    val error: String? = null,
    val createdAt: LocalDateTime
) {
    companion object {
        fun create(identifier: String, status: IdempotencyStatusEnum): IdempotencyDomain {
            return IdempotencyDomain(
                identifier = identifier,
                status = status,
                createdAt = LocalDateTime.now()
            )
        }

        fun restore(identifier: String, status: IdempotencyStatusEnum, result: String?, error: String?, createdAt: LocalDateTime): IdempotencyDomain {
            return IdempotencyDomain(identifier, status, result, error, createdAt)
        }
    }
}
