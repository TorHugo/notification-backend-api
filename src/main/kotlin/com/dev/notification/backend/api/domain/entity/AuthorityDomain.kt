package com.dev.notification.backend.api.domain.entity

import com.dev.notification.backend.api.domain.utils.IdentifierUtils
import java.time.LocalDateTime
import java.util.UUID

data class AuthorityDomain(
    override val identifier: UUID,
    val name: String,
    val description: String,
    val active: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
)  : AggregateRoot<UUID>(){

    companion object {
        fun restore(
            identifier: String,
            name: String,
            description: String,
            active: Boolean,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime?
        ): AuthorityDomain{
            return AuthorityDomain(
                IdentifierUtils.byString(identifier),
                name,
                description,
                active,
                createdAt,
                updatedAt
            )
        }
    }
}