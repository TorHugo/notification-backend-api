package com.dev.notification.backend.api.domain.service

import com.dev.notification.backend.api.infrastructure.api.dto.request.SignInUserDTO
import com.dev.notification.backend.api.infrastructure.api.dto.response.UserSuccessfullyDTO

interface SignInService {
    fun execute(entry: SignInUserDTO) : UserSuccessfullyDTO
}