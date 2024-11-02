package com.dev.notification.backend.api.infrastructure.repository.redis.models

import com.dev.notification.backend.api.domain.enums.IdempotencyStatusEnum
import java.time.LocalDateTime

data class IdempotencyEntity(
    val prefix: String,
    val key: String,
    val status: IdempotencyStatusEnum,
    val result: String? = null,
    val error: String? = null,
    val createdAt: LocalDateTime
) : RedisEntity {
    override fun getEntityKey(): String = key
    override fun getEntityPrefix(): String = prefix
}
