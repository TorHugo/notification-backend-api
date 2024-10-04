package com.dev.notification.backend.api.domain.utils

import java.util.UUID

object IdentifierUtils {
    fun generated() : UUID {
        return UUID.randomUUID()
    }

    fun byString(value: String) : UUID {
        return UUID.fromString(value)
    }
}