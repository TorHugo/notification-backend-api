package com.dev.notification.backend.api.domain.objects

import com.dev.notification.backend.api.domain.exception.template.DomainException
import java.util.*

data class Email(val value: String) {
    init {
        Objects.requireNonNull(value, "Email value cannot be null")
        validateValue(value)
    }

    companion object {
        fun validateValue(currentValue: String) {
            if (!currentValue.matches(Regex("^(.+)@(.+)$"))) {
                throw DomainException("This email is not valid.", currentValue)
            }
        }
    }
}
