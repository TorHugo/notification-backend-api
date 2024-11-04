package com.dev.notification.backend.api.infrastructure.repository.redis

import com.dev.notification.backend.api.infrastructure.repository.redis.models.RedisEntity
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
abstract class RedisTemplateRepository<T : RedisEntity>(
    @Value("\${spring.data.redis.timeout.default}")
    protected val timeInvalidKey: Long,
    val redisTemplate: RedisTemplate<String, String>,
    val gson: Gson,
) {
    @Suppress("kotlin:S6518")
    fun save(entity: T) {
        val value = serialize(entity)
        redisTemplate.opsForValue().set(
            identifier(entity.getEntityPrefix(), entity.getEntityKey()),
            value,
            timeInvalidKey,
            TimeUnit.HOURS,
        )
    }

    fun get(prefix: String, key: String, type: Class<T>): T? {
        val json = redisTemplate.opsForValue()[identifier(prefix, key)]
        return json?.let { deserialize(it, type) }
    }

    fun serialize(entity: T): String {
        return gson.toJson(entity)
    }

    fun deserialize(json: String, type: Class<T>): T {
        return gson.fromJson(json, type)
    }

    fun identifier(prefix: String, key: String): String {
        return "${prefix}_$key"
    }
}
