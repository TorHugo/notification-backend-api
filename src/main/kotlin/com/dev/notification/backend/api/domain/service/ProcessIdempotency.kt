package com.dev.notification.backend.api.domain.service

interface ProcessIdempotency {
    fun execute(idempotencyKey: String, shouldRetry: Boolean, operation: () -> Any): Any
}