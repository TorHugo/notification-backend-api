package com.dev.notification.backend.api.infrastructure.repository.db.models

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "authority_to_user_tb")
@Entity
class AuthorityToUserEntity(
    @EmbeddedId
    val compositeKey: CompositeKey,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
)

@Embeddable
class CompositeKey(
    @Column(name = "user_identifier", nullable = false)
    val userIdentifier: String,

    @Column(name = "authority_identifier", nullable = false)
    val authorityIdentifier: String,
)
