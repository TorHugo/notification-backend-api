package com.dev.notification.backend.api.infrastructure.exception.models

data class ExceptionData(
    val error: String,
    val message: String,
    val path: String
)
