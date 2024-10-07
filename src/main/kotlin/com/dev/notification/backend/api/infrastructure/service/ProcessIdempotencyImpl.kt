package com.dev.notification.backend.api.infrastructure.service

import com.dev.notification.backend.api.application.FindExistsIdempotency
import com.dev.notification.backend.api.application.SaveIdempotency
import com.dev.notification.backend.api.domain.entity.IdempotencyDomain
import com.dev.notification.backend.api.domain.enums.IdempotencyStatus
import com.dev.notification.backend.api.domain.exception.template.ServiceException
import com.dev.notification.backend.api.domain.service.ProcessIdempotency
import com.dev.notification.backend.api.infrastructure.api.dto.response.DefaultDTO
import com.google.gson.Gson
import org.springframework.stereotype.Service

@Service
class ProcessIdempotencyImpl(
    val findExistsIdempotency: FindExistsIdempotency,
    val saveIdempotency: SaveIdempotency,
    val gson: Gson
): ProcessIdempotency {
    override fun execute(idempotencyKey: String,
                         shouldRetry: Boolean,
                         operation: () -> Any): Any {
        val existsIdempotency = findExistsIdempotency.execute(idempotencyKey)
        return when (existsIdempotency?.status) {
            IdempotencyStatus.COMPLETED -> {
                val successResult = existsIdempotency.result ?: throw ServiceException("Completed key must have result!")
                val finalResult = gson.fromJson(successResult, DefaultDTO::class.java)
                finalResult
            }
            IdempotencyStatus.FAILED -> {
                if (shouldRetry) {
                    processOperation(idempotencyKey, operation)
                } else {
                    throw ServiceException("Operation previously failed: ${existsIdempotency.error}!")
                }
            }
            IdempotencyStatus.PROCESSING -> {
                if (shouldRetry) {
                    processOperation(idempotencyKey, operation)
                } else {
                    throw ServiceException("Operation is already in processing state!")
                }
            }
            else -> processOperation(idempotencyKey, operation)
        }
    }

    private fun processOperation(idempotencyKey: String, operation: () -> Any): Any {
        val processingKey = createProcessingIdempotency(idempotencyKey)
        return try {
            val result = operation()
            createIdempotency(
                processingKey,
                result,
                null,
                IdempotencyStatus.COMPLETED
            )
            result
        } catch (e: Exception) {
            createIdempotency(
                processingKey,
                null,
                e.message,
                IdempotencyStatus.FAILED
            )
            throw e
        }
    }

    private fun createProcessingIdempotency(idempotencyKey: String): IdempotencyDomain {
        val processingKey = IdempotencyDomain.create(
            idempotencyKey,
            IdempotencyStatus.PROCESSING
        )
        saveIdempotency.execute(processingKey)
        return processingKey
    }

    private fun <T> createIdempotency(
        processingIdempotency: IdempotencyDomain,
        result: T?,
        error: String?,
        idempotencyStatus: IdempotencyStatus
    ) {
        val completedKey = processingIdempotency.copy(
            status = idempotencyStatus,
            result = result?.let { gson.toJson(it) },
            error = error
        )
        saveIdempotency.execute(completedKey)
    }

}