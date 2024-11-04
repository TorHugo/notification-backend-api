package com.dev.notification.backend.api.domain.entity

import java.time.LocalDateTime
import java.util.*

data class AuthorityToUserDomain(
    val userIdentifier: UUID,
    val authorityIdentifier: UUID,
    val createdAt: LocalDateTime,
) {
    fun getUserIdentifier(): String {
        return userIdentifier.toString()
    }

    fun getAuthorityIdentifier(): String {
        return authorityIdentifier.toString()
    }

    companion object {
        fun create(
            userIdentifier: UUID,
            authorityIdentifier: UUID,
        ): AuthorityToUserDomain {
            return AuthorityToUserDomain(
                userIdentifier,
                authorityIdentifier,
                createdAt = LocalDateTime.now(),
            )
        }
    }
}
