package com.dev.notification.backend.api.infrastructure.repository.db

import com.dev.notification.backend.api.infrastructure.repository.db.models.NotificationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<NotificationEntity, String> {
}