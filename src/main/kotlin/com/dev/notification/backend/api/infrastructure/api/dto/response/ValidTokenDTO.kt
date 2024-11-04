package com.dev.notification.backend.api.infrastructure.api.dto.response

import java.time.LocalDateTime

data class ValidTokenDTO(
    val currentToken: String,
    val validated: Boolean,
    val expirationTime: LocalDateTime,
    val authorities: List<String>,
)
