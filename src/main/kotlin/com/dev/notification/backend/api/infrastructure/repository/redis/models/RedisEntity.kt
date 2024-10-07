package com.dev.notification.backend.api.infrastructure.repository.redis.models

interface RedisEntity {
    fun getEntityKey(): String
    fun getEntityPrefix(): String
}