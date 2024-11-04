package com.dev.notification.backend.api.infrastructure.repository.db.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "notification_tb")
@Entity
class NotificationEntity(
    @Id
    val identifier: String,
    val contact: String,
    val subject: String,
    @Column(columnDefinition = "TEXT")
    val template: String,
    @Column(columnDefinition = "TEXT")
    val parameters: String,
    val createdAt: LocalDateTime,
)
