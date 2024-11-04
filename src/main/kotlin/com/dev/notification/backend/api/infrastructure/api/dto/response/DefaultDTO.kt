package com.dev.notification.backend.api.infrastructure.api.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class DefaultDTO<T>(
    val status: Int,
    val data: T,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    val timestamp: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun <T> success(data: T): DefaultDTO<T> = DefaultDTO(
            status = 200,
            data = data,
        )

        fun <T> created(data: T): DefaultDTO<T> = DefaultDTO(
            status = 201,
            data = data,
        )
    }
}
