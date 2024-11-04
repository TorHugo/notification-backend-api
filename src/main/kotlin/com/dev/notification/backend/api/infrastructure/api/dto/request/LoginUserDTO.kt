package com.dev.notification.backend.api.infrastructure.api.dto.request

import com.dev.notification.backend.api.domain.annotation.Password
import jakarta.validation.constraints.NotBlank

class LoginUserDTO(
    @NotBlank val email: String,
    @Password val password: String,
)
