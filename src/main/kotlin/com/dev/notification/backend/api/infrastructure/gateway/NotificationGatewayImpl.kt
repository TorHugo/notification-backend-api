package com.dev.notification.backend.api.infrastructure.gateway

import com.dev.notification.backend.api.domain.entity.NotificationDomain
import com.dev.notification.backend.api.domain.gateway.NotificationGateway
import com.dev.notification.backend.api.infrastructure.repository.db.NotificationRepository
import com.dev.notification.backend.api.infrastructure.repository.db.mapper.NotificationEntityMapper
import org.springframework.stereotype.Component

@Component
class NotificationGatewayImpl(
    val notificationRepository: NotificationRepository
): NotificationGateway {
    override fun save(domain: NotificationDomain) {
        val entity = NotificationEntityMapper.fromAggregate(domain)
        notificationRepository.save(entity)
    }
}