package com.dev.notification.backend.api.infrastructure.repository.db.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "authority_tb")
@Entity
class AuthorityEntity(
    @Id
    val identifier: String,
    val name: String,
    val description: String,
    val active: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
)
