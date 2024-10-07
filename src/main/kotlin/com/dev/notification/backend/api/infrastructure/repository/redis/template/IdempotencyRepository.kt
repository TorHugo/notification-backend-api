package com.dev.notification.backend.api.infrastructure.repository.redis.template

import com.dev.notification.backend.api.infrastructure.repository.redis.RedisTemplateRepository
import com.dev.notification.backend.api.infrastructure.repository.redis.models.IdempotencyEntity
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class IdempotencyRepository(
    @Value("\${spring.data.redis.timeout.default}")
    timeInvalidKey: Long,
    redisTemplate: RedisTemplate<String, String>,
    gson: Gson
) : RedisTemplateRepository<IdempotencyEntity>(timeInvalidKey, redisTemplate, gson) {

    override fun deserialize(json: String, type: Class<IdempotencyEntity>): IdempotencyEntity {
        return super.deserialize(json, IdempotencyEntity::class.java)
    }
}