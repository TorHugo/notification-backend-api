package com.dev.notification.backend.api.infrastructure.mappers

import com.dev.notification.backend.api.domain.entity.UserDomain
import com.dev.notification.backend.api.infrastructure.api.dto.request.SignInUserDTO

object UserToDomainMapper {
    fun toUserDomain(entry: SignInUserDTO, bcryptPassword: String) : UserDomain {
        return UserDomain.create(
            entry.email,
            bcryptPassword,
            entry.firstName,
            entry.lastName,
            false
        )
    }
}