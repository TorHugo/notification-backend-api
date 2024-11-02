package com.dev.notification.backend.api.domain.service

import com.dev.notification.backend.api.domain.entity.UserDomain

interface SignInService {
    fun execute(domain: UserDomain): String
}
