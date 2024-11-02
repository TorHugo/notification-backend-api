package com.dev.notification.backend.api.infrastructure.repository.db.mapper

import com.dev.notification.backend.api.domain.entity.NotificationDomain
import com.dev.notification.backend.api.infrastructure.repository.db.models.NotificationEntity

object NotificationEntityMapper {
    fun fromAggregate(domain: NotificationDomain): NotificationEntity {
        return NotificationEntity(
            domain.getIdentifier(),
            domain.contact,
            domain.subject,
            domain.template,
            domain.getParameters(),
            domain.createdAt
        )
    }
}
