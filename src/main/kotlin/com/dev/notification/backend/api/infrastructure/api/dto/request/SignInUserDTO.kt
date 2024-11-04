package com.dev.notification.backend.api.infrastructure.api.dto.request

import com.dev.notification.backend.api.domain.annotation.Password
import com.fasterxml.jackson.annotation.JsonProperty

data class SignInUserDTO(
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String,
    val email: String,
    @Password val password: String,
)
