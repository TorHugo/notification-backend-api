package com.dev.notification.backend.api.infrastructure.interceptor

import com.dev.notification.backend.api.domain.exception.template.InvalidArgumentException
import com.dev.notification.backend.api.domain.service.ProcessIdempotency
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class IdempotencyInterceptor(
    private val processingIdempotency: ProcessIdempotency,
) {
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    fun intercept(joinPoint: ProceedingJoinPoint): Any {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val idempotencyKey = request.getHeader("Idempotency-Key")
        if (idempotencyKey.isNullOrBlank()) throw InvalidArgumentException("Idempotency-Key cannot be null or empty to POST request!")
        val shouldRetry = request.getHeader("Notification-Should-Retry")?.toBoolean() ?: false
        return processingIdempotency.execute(idempotencyKey, shouldRetry) {
            joinPoint.proceed()
        }
    }
}
