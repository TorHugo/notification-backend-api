package com.dev.notification.backend.api.infrastructure.repository.db.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "users_tb")
@Entity
class UserEntity (
    @Id
    val identifier: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val active: Boolean,
    val admin: Boolean,
    val confirmed: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val lastAccess: LocalDateTime?
)
