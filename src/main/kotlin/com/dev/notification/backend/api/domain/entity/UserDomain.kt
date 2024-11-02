package com.dev.notification.backend.api.domain.entity

import com.dev.notification.backend.api.domain.utils.IdentifierUtils
import com.dev.notification.backend.api.domain.value.`object`.Email
import java.time.LocalDateTime
import java.util.*

data class UserDomain(
    override val identifier: UUID,
    val email: Email,
    var password: String,
    val firstName: String,
    val lastName: String,
    var confirmed: Boolean,
    val active: Boolean,
    val admin: Boolean,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime?,
    var lastAccess: LocalDateTime?
) : AggregateRoot<UUID>() {
    fun updateLastAccess() {
        lastAccess = LocalDateTime.now()
    }

    fun fullName(): String{
        return "$firstName $lastName"
    }

    companion object {
        fun create(
                email: String,
                password: String,
                firstName: String,
                lastName: String,
                admin: Boolean
        ): UserDomain {
            return UserDomain(
                identifier = IdentifierUtils.generated(),
                email = Email(email),
                password = password,
                firstName = firstName,
                lastName = lastName,
                active = true,
                confirmed = false,
                admin = admin,
                createdAt = LocalDateTime.now(),
                updatedAt = null,
                lastAccess = null
            )
        }

        fun restore(
            identifier: String,
            email: String,
            password: String,
            firstName: String,
            lastName: String,
            active: Boolean,
            admin: Boolean,
            confirmed: Boolean,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime?,
            lastAccess: LocalDateTime?
        ): UserDomain {
            return UserDomain(
                IdentifierUtils.byString(identifier),
                email = Email(email),
                password = password,
                firstName = firstName,
                lastName = lastName,
                active = active,
                admin = admin,
                confirmed = confirmed,
                createdAt = createdAt,
                updatedAt = updatedAt,
                lastAccess = lastAccess
            )
        }
    }
}