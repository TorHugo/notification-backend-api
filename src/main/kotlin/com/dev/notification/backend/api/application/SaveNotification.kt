package com.dev.notification.backend.api.application

import com.dev.notification.backend.api.domain.entity.NotificationDomain
import com.dev.notification.backend.api.domain.gateway.NotificationGateway
import org.springframework.stereotype.Component

@Component
class SaveNotification(
    val notificationGateway: NotificationGateway
) {
    fun execute(domain: NotificationDomain) {
        notificationGateway.save(domain)
    }
}
